package com.github.barcochrist.satisfactionsurvey.service.impl;

import com.github.barcochrist.satisfactionsurvey.entity.QuestionJpa;
import com.github.barcochrist.satisfactionsurvey.model.Question;
import com.github.barcochrist.satisfactionsurvey.model.enums.QuestionType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface CommonUnitTest {

  String MUST_NOT_BE_NULL = "must not be null";
  String VALUE_DOESN_T_MATCH = "value doesn't match";
  String MUST_BE_NULL = "must be null";

  String QUESTION_1_ID = "2789aaf7-0118-47ad-9bbd-aa21559c231a";
  Question QUESTION_1 = QuestionJpa.builder()
      .id(QUESTION_1_ID)
      .title("Pregunta X")
      .type(QuestionType.MULTIPLE_CHOICE)
      .isRequired(true)
      .build();

  String QUESTION_2_ID = "2789aaf7-0118-47ad-9bbd-aa21559c231b";
  Question QUESTION_2 = QuestionJpa.builder()
      .id(QUESTION_2_ID)
      .title("Pregunta Y")
      .type(QuestionType.OPEN)
      .isRequired(false)
      .build();

  Pageable PAGEABLE = PageRequest.of(1, 10);
}