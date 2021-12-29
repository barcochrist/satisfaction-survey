package com.github.barcochrist.satisfactionsurvey.service;

import com.github.barcochrist.satisfactionsurvey.model.Answer;
import javax.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AnswerService {

  @NotNull
  Page<Answer> findAll(Pageable pageable);
}