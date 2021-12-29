package com.github.barcochrist.satisfactionsurvey.model;

import java.util.Optional;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public interface AnswerQuestion {

  @NotBlank
  String getId();

  @NotBlank
  String getAnswerId();

  @NotBlank
  String getQuestionId();

  @NotNull
  Optional<String> getResponse();
}