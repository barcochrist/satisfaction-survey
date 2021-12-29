package com.github.barcochrist.satisfactionsurvey.service;

import com.github.barcochrist.satisfactionsurvey.model.Question;
import com.github.barcochrist.satisfactionsurvey.model.QuestionOption;
import java.util.List;
import java.util.Optional;
import javax.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuestionService {

  @NotNull
  Optional<Question> findById(String id);

  @NotNull
  List<QuestionOption> findOptionsByQuestionId(String questionId);

  @NotNull
  Page<Question> findAll(Pageable pageable);
}