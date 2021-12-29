package com.github.barcochrist.satisfactionsurvey.repository.impl;

import com.github.barcochrist.satisfactionsurvey.model.QuestionOption;
import com.github.barcochrist.satisfactionsurvey.repository.QuestionOptionRepository;
import com.github.barcochrist.satisfactionsurvey.repository.jpa.QuestionOptionJpaRepository;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.constraints.NotNull;
import org.springframework.stereotype.Repository;

@Repository
public class QuestionOptionRepositoryImpl implements QuestionOptionRepository {

  private final QuestionOptionJpaRepository questionOptionJpaRepository;

  /**
   * All arguments constructor.
   */
  public QuestionOptionRepositoryImpl(QuestionOptionJpaRepository questionOptionJpaRepository) {
    this.questionOptionJpaRepository = questionOptionJpaRepository;
  }

  @NotNull
  @Override
  public List<QuestionOption> findByQuestionId(String questionId) {
    return questionOptionJpaRepository
        .findByQuestionId(questionId)
        .stream()
        .collect(Collectors.toList());
  }

  @NotNull
  @Override
  public List<QuestionOption> findSeveralOptions(List<String> optionIds) {
    return questionOptionJpaRepository.findAllById(optionIds)
        .stream()
        .collect(Collectors.toList());
  }
}