package com.github.barcochrist.satisfactionsurvey.controller;

import com.github.barcochrist.satisfactionsurvey.model.Answer;
import com.github.barcochrist.satisfactionsurvey.model.enums.QuestionType;
import com.github.barcochrist.satisfactionsurvey.resource.AnswerDraftResource;
import com.github.barcochrist.satisfactionsurvey.resource.AnswerQuestionResource;
import com.github.barcochrist.satisfactionsurvey.resource.AnswerResource;
import com.github.barcochrist.satisfactionsurvey.resource.QuestionOptionResource;
import com.github.barcochrist.satisfactionsurvey.service.AnswerService;
import com.github.barcochrist.satisfactionsurvey.service.QuestionService;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/answers")
public class AnswerRestController {

  private final AnswerService answerService;
  private final QuestionService questionService;

  /**
   * All arguments constructor.
   */
  public AnswerRestController(
      AnswerService answerService,
      QuestionService questionService
  ) {
    this.answerService = answerService;
    this.questionService = questionService;
  }

  /**
   * Find all {@link Answer}s.
   */
  @GetMapping
  public ResponseEntity<List<AnswerResource>> findAll(Pageable pageable) {
    Page<AnswerResource> response = answerService
        .findAll(pageable)
        .map(this::instanceAnswerResource);

    return ResponseEntity.ok(response.getContent());
  }

  /**
   * Create a new full {@link Answer}.
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<AnswerResource> create(
      @RequestBody @Valid AnswerDraftResource draft
  ) {
    var answer = answerService.create(draft);
    return ResponseEntity.ok(instanceAnswerResource(answer));
  }

  /**
   * Instance a new {@link AnswerResource}.
   *
   * @param response An {@link Answer} instance
   * @return A new full {@link AnswerResource} instance
   */
  @NotNull
  private AnswerResource instanceAnswerResource(@NotNull Answer response) {
    var answers = answerService
        .findAnswerQuestionsByAnswerId(response.getId())
        .stream()
        .map(answerQuestion -> {
          var question = questionService.findById(answerQuestion.getQuestionId());

          if (question.isPresent()) {
            if (QuestionType.MULTIPLE_CHOICE.equals(question.get().getType())) {
              var optionIds = List.of(answerQuestion.getResponse().split(","));

              var options = questionService
                  .findSeveralOptions(optionIds)
                  .stream()
                  .map(QuestionOptionResource::from)
                  .collect(Collectors.toList());

              return AnswerQuestionResource.from(
                  answerQuestion,
                  null,
                  question.get().getTitle(),
                  question.get().getType(),
                  options
              );
            } else {
              return AnswerQuestionResource.from(
                  answerQuestion,
                  answerQuestion.getResponse(),
                  question.get().getTitle(),
                  question.get().getType(),
                  null
              );
            }
          } else {
            return AnswerQuestionResource.from(
                answerQuestion,
                answerQuestion.getResponse(),
                null,
                null,
                null
            );
          }
        })
        .collect(Collectors.toList());

    return AnswerResource.from(response, answers);
  }
}