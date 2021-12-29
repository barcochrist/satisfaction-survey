package com.github.barcochrist.satisfactionsurvey.repository;

import com.github.barcochrist.satisfactionsurvey.model.AnswerQuestion;
import java.util.List;
import javax.validation.constraints.NotNull;

public interface AnswerQuestionRepository {

  @NotNull
  List<AnswerQuestion> findByAnswerId(String answerId);
}