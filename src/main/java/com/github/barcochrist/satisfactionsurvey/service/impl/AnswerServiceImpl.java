package com.github.barcochrist.satisfactionsurvey.service.impl;

import com.github.barcochrist.satisfactionsurvey.entity.AnswerJpa;
import com.github.barcochrist.satisfactionsurvey.entity.AnswerQuestionJpa;
import com.github.barcochrist.satisfactionsurvey.model.Answer;
import com.github.barcochrist.satisfactionsurvey.model.AnswerQuestion;
import com.github.barcochrist.satisfactionsurvey.model.enums.QuestionType;
import com.github.barcochrist.satisfactionsurvey.repository.AnswerQuestionRepository;
import com.github.barcochrist.satisfactionsurvey.repository.AnswerRepository;
import com.github.barcochrist.satisfactionsurvey.repository.QuestionRepository;
import com.github.barcochrist.satisfactionsurvey.resource.AnswerDraftResource;
import com.github.barcochrist.satisfactionsurvey.resource.AnswerQuestionDraftResource;
import com.github.barcochrist.satisfactionsurvey.service.AnswerService;
import java.util.List;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AnswerServiceImpl implements AnswerService {

  private final AnswerRepository answerRepository;
  private final AnswerQuestionRepository answerQuestionRepository;
  private final QuestionRepository questionRepository;

  /**
   * All arguments constructor.
   */
  public AnswerServiceImpl(
      AnswerRepository answerRepository,
      AnswerQuestionRepository answerQuestionRepository,
      QuestionRepository questionRepository
  ) {
    this.answerRepository = answerRepository;
    this.answerQuestionRepository = answerQuestionRepository;
    this.questionRepository = questionRepository;
  }

  @NotNull
  @Override
  public Answer create(AnswerDraftResource answer) {
    var answerByEmail = answerRepository.findByEmail(answer.getEmail());
    if (answerByEmail.isPresent()) {
      throw new IllegalArgumentException(
          String.format("The customer with the email \"%s\" has already done the survey",
              answer.getEmail()));
    }

    validateMandatoryQuestions(answer.getAnswers());

    var answerId = UUID.randomUUID().toString();
    answer
        .getAnswers()
        .forEach(aqDraft -> {
          var question = questionRepository
              .findById(aqDraft.getQuestionId())
              .orElseThrow(() -> new IllegalArgumentException(
                  String.format("Question with identifier %s, not found",
                      aqDraft.getQuestionId())));

          if (QuestionType.OPEN.equals(question.getType())
              && (aqDraft.getResponse() == null || aqDraft.getResponse().isBlank())) {
            throw new IllegalArgumentException(
                String.format("Please give an answer to the question: \"%s\"",
                    question.getTitle()));
          }

          if (QuestionType.MULTIPLE_CHOICE.equals(question.getType())
              && (aqDraft.getOptionIds() == null || aqDraft.getOptionIds().isEmpty())) {
            throw new IllegalArgumentException(String.format(
                "Please select at least one option for question \"%s\"", question.getTitle()));
          }

          var response = QuestionType.OPEN.equals(question.getType())
              ? aqDraft.getResponse()
              : String.join(",", aqDraft.getOptionIds());

          var answerQuestionJpa = AnswerQuestionJpa.builder()
              .id(UUID.randomUUID().toString())
              .answerId(answerId)
              .questionId(question.getId())
              .response(response)
              .build();

          answerQuestionRepository.create(answerQuestionJpa);
        });

    return answerRepository
        .create(
            AnswerJpa.builder()
                .id(answerId)
                .email(answer.getEmail())
                .customerName(answer.getCustomerName())
                .build()
        );
  }

  @NotNull
  @Override
  public Page<Answer> findAll(Pageable pageable) {
    return answerRepository.findAll(pageable);
  }

  @NotNull
  @Override
  public List<AnswerQuestion> findAnswerQuestionsByAnswerId(String answerId) {
    return answerQuestionRepository.findByAnswerId(answerId);
  }

  /**
   * Validate all mandatory or required questions.
   *
   * @param answers A list of Answers.
   */
  private void validateMandatoryQuestions(List<AnswerQuestionDraftResource> answers) {
    questionRepository
        .findRequired()
        .forEach(requiredQuestion -> {
          var count = answers
              .stream()
              .filter(aq -> aq.getQuestionId().equals(requiredQuestion.getId()))
              .count();
          if (count <= 0) {
            throw new IllegalArgumentException(
                String.format("Please answer the following mandatory question: \"%s\"",
                    requiredQuestion.getTitle()));
          }
        });
  }
}