package com.github.barcochrist.satisfactionsurvey.service;

import com.github.barcochrist.satisfactionsurvey.model.Answer;
import com.github.barcochrist.satisfactionsurvey.model.AnswerQuestion;
import com.github.barcochrist.satisfactionsurvey.resource.AnswerDraftResource;
import java.util.List;
import javax.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AnswerService {

  @NotNull
  Answer create(AnswerDraftResource answer);

  @NotNull
  Page<Answer> findAll(Pageable pageable);

  @NotNull
  List<AnswerQuestion> findAnswerQuestionsByAnswerId(String answerId);
}