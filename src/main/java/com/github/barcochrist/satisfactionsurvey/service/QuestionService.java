package com.github.barcochrist.satisfactionsurvey.service;

import com.github.barcochrist.satisfactionsurvey.model.Question;
import com.github.barcochrist.satisfactionsurvey.model.QuestionOption;
import java.util.List;
import java.util.Optional;
import javax.validation.constraints.NotNull;

public interface QuestionService {

  @NotNull
  Optional<Question> findById(String id);

  @NotNull
  List<QuestionOption> findOptionsByQuestionId(String questionId);
}