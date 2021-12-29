package com.github.barcochrist.satisfactionsurvey.repository.impl;

import com.github.barcochrist.satisfactionsurvey.entity.AnswerJpa;
import com.github.barcochrist.satisfactionsurvey.model.Answer;
import com.github.barcochrist.satisfactionsurvey.repository.AnswerRepository;
import com.github.barcochrist.satisfactionsurvey.repository.jpa.AnswerJpaRepository;
import java.util.Optional;
import javax.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class AnswerRepositoryImpl implements AnswerRepository {

  private final AnswerJpaRepository answerJpaRepository;

  /**
   * All arguments constructor.
   */
  public AnswerRepositoryImpl(AnswerJpaRepository answerJpaRepository) {
    this.answerJpaRepository = answerJpaRepository;
  }

  @NotNull
  @Override
  public Optional<Answer> findByEmail(String email) {
    return answerJpaRepository.findByEmailIgnoreCase(email);
  }

  @NotNull
  @Override
  public Page<Answer> findAll(Pageable pageable) {
    return answerJpaRepository
        .findAll(pageable)
        .map(answerJpa -> answerJpa);
  }

  @Override
  public Answer create(Answer answer) {
    return answerJpaRepository.save(AnswerJpa.from(answer));
  }
}