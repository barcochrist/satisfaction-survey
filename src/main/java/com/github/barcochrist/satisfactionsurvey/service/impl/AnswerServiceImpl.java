package com.github.barcochrist.satisfactionsurvey.service.impl;

import com.github.barcochrist.satisfactionsurvey.model.Answer;
import com.github.barcochrist.satisfactionsurvey.model.AnswerQuestion;
import com.github.barcochrist.satisfactionsurvey.repository.AnswerQuestionRepository;
import com.github.barcochrist.satisfactionsurvey.repository.AnswerRepository;
import com.github.barcochrist.satisfactionsurvey.service.AnswerService;
import java.util.List;
import javax.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AnswerServiceImpl implements AnswerService {

  private final AnswerRepository answerRepository;
  private final AnswerQuestionRepository answerQuestionRepository;

  /**
   * All arguments constructor.
   */
  public AnswerServiceImpl(
      AnswerRepository answerRepository,
      AnswerQuestionRepository answerQuestionRepository
  ) {
    this.answerRepository = answerRepository;
    this.answerQuestionRepository = answerQuestionRepository;
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
}