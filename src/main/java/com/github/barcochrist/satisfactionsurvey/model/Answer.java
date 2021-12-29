package com.github.barcochrist.satisfactionsurvey.model;

import javax.validation.constraints.NotBlank;

public interface Answer {

  @NotBlank
  String getId();

  @NotBlank
  String getEmail();

  @NotBlank
  String getCustomerName();
}