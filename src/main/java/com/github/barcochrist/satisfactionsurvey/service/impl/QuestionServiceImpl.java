package com.github.barcochrist.satisfactionsurvey.service.impl;

import com.github.barcochrist.satisfactionsurvey.model.Question;
import com.github.barcochrist.satisfactionsurvey.repository.QuestionRepository;
import com.github.barcochrist.satisfactionsurvey.service.QuestionService;
import java.util.Optional;
import javax.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl implements QuestionService {

  private final QuestionRepository questionRepository;

  /**
   * All arguments constructor.
   */
  public QuestionServiceImpl(QuestionRepository questionRepository) {
    this.questionRepository = questionRepository;
  }

  @NotNull
  @Override
  public Optional<Question> findById(String id) {
    return questionRepository.findById(id);
  }
}