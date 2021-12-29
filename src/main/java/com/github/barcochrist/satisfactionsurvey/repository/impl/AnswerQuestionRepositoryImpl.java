package com.github.barcochrist.satisfactionsurvey.repository.impl;

import com.github.barcochrist.satisfactionsurvey.entity.AnswerQuestionJpa;
import com.github.barcochrist.satisfactionsurvey.model.AnswerQuestion;
import com.github.barcochrist.satisfactionsurvey.repository.AnswerQuestionRepository;
import com.github.barcochrist.satisfactionsurvey.repository.jpa.AnswerQuestionJpaRepository;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.constraints.NotNull;
import org.springframework.stereotype.Repository;

@Repository
public class AnswerQuestionRepositoryImpl implements AnswerQuestionRepository {

  private final AnswerQuestionJpaRepository answerQuestionJpaRepository;

  /**
   * All arguments constructor.
   */
  public AnswerQuestionRepositoryImpl(AnswerQuestionJpaRepository answerQuestionJpaRepository) {
    this.answerQuestionJpaRepository = answerQuestionJpaRepository;
  }

  @NotNull
  @Override
  public List<AnswerQuestion> findByAnswerId(String answerId) {
    return answerQuestionJpaRepository
        .findByAnswerId(answerId)
        .stream()
        .collect(Collectors.toList());
  }

  @NotNull
  @Override
  public AnswerQuestion create(AnswerQuestion answerQuestion) {
    return answerQuestionJpaRepository.save(AnswerQuestionJpa.from(answerQuestion));
  }
}
