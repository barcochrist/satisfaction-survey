package com.github.barcochrist.satisfactionsurvey.model;

import com.github.barcochrist.satisfactionsurvey.model.enums.QuestionType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public interface Question {

  @NotBlank
  String getId();

  @NotBlank
  String getTitle();

  @NotNull
  QuestionType getType();

  @NotNull
  Boolean getIsRequired();
}
