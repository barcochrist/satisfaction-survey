package com.github.barcochrist.satisfactionsurvey.repository;

import com.github.barcochrist.satisfactionsurvey.model.Question;
import java.util.Optional;
import javax.validation.constraints.NotNull;

public interface QuestionRepository {

  @NotNull
  Optional<Question> findById(String id);
}