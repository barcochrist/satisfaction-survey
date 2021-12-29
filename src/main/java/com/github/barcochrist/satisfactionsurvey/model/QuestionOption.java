package com.github.barcochrist.satisfactionsurvey.model;

import javax.validation.constraints.NotBlank;

public interface QuestionOption {

  @NotBlank
  String getId();

  @NotBlank
  String getQuestionId();

  @NotBlank
  String getName();
}