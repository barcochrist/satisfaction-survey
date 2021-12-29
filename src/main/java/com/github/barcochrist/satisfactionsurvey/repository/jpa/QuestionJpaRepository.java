package com.github.barcochrist.satisfactionsurvey.repository.jpa;

import com.github.barcochrist.satisfactionsurvey.entity.QuestionJpa;
import java.util.List;
import javax.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionJpaRepository extends JpaRepository<QuestionJpa, String> {

  @NotNull
  List<QuestionJpa> findByIsRequiredTrue();
}