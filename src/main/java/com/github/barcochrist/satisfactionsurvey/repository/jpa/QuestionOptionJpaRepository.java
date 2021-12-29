package com.github.barcochrist.satisfactionsurvey.repository.jpa;

import com.github.barcochrist.satisfactionsurvey.entity.QuestionOptionJpa;
import java.util.List;
import javax.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionOptionJpaRepository extends JpaRepository<QuestionOptionJpa, String> {

  @NotNull
  List<QuestionOptionJpa> findByQuestionId(String questionId);
}
