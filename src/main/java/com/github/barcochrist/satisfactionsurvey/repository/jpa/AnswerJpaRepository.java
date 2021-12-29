package com.github.barcochrist.satisfactionsurvey.repository.jpa;

import com.github.barcochrist.satisfactionsurvey.entity.AnswerJpa;
import com.github.barcochrist.satisfactionsurvey.model.Answer;
import java.util.Optional;
import javax.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerJpaRepository extends JpaRepository<AnswerJpa, String> {

  @NotNull
  Optional<Answer> findByEmailIgnoreCase(String email);
}