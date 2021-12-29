package com.github.barcochrist.satisfactionsurvey.repository.jpa;

import com.github.barcochrist.satisfactionsurvey.entity.AnswerQuestionJpa;
import java.util.List;
import javax.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerQuestionJpaRepository extends JpaRepository<AnswerQuestionJpa, String> {

  @NotNull
  List<AnswerQuestionJpa> findByAnswerId(String answerId);
}