package com.github.barcochrist.satisfactionsurvey.repository.impl;

import com.github.barcochrist.satisfactionsurvey.model.Question;
import com.github.barcochrist.satisfactionsurvey.repository.QuestionRepository;
import com.github.barcochrist.satisfactionsurvey.repository.jpa.QuestionJpaRepository;
import java.util.Optional;
import javax.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class QuestionRepositoryImpl implements QuestionRepository {

  private final QuestionJpaRepository questionJpaRepository;

  /**
   * All arguments constructor.
   */
  public QuestionRepositoryImpl(QuestionJpaRepository questionJpaRepository) {
    this.questionJpaRepository = questionJpaRepository;
  }

  @NotNull
  @Override
  public Optional<Question> findById(String id) {
    return questionJpaRepository
        .findById(id)
        .map(questionJpa -> questionJpa);
  }

  @NotNull
  @Override
  public Page<Question> findAll(Pageable pageable) {
    return questionJpaRepository
        .findAll(pageable)
        .map(questionJpa -> questionJpa);
  }
}