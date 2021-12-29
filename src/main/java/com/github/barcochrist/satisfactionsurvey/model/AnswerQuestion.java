package com.github.barcochrist.satisfactionsurvey.model;

import javax.validation.constraints.NotBlank;

public interface AnswerQuestion {

  @NotBlank
  String getId();

  @NotBlank
  String getAnswerId();

  @NotBlank
  String getQuestionId();

  @NotBlank
  String getResponse();
}