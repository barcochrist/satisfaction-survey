package com.github.barcochrist.satisfactionsurvey.repository;

import com.github.barcochrist.satisfactionsurvey.model.QuestionOption;
import java.util.List;
import javax.validation.constraints.NotNull;

public interface QuestionOptionRepository {

  @NotNull
  List<QuestionOption> findByQuestionId(String questionId);

  @NotNull
  List<QuestionOption> findSeveralOptions(List<String> optionIds);
}