package com.github.barcochrist.satisfactionsurvey.service.impl;

import com.github.barcochrist.satisfactionsurvey.model.Answer;
import com.github.barcochrist.satisfactionsurvey.repository.AnswerRepository;
import com.github.barcochrist.satisfactionsurvey.service.AnswerService;
import javax.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AnswerServiceImpl implements AnswerService {

  private final AnswerRepository answerRepository;

  /**
   * All arguments constructor.
   */
  public AnswerServiceImpl(AnswerRepository answerRepository) {
    this.answerRepository = answerRepository;
  }

  @NotNull
  @Override
  public Page<Answer> findAll(Pageable pageable) {
    return answerRepository.findAll(pageable);
  }
}