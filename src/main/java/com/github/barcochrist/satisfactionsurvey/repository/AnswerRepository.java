package com.github.barcochrist.satisfactionsurvey.repository;

import com.github.barcochrist.satisfactionsurvey.model.Answer;
import java.util.Optional;
import javax.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AnswerRepository {

  @NotNull
  Optional<Answer> findByEmail(String email);

  @NotNull
  Page<Answer> findAll(Pageable pageable);

  @NotNull
  Answer create(Answer answer);
}