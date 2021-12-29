package com.github.barcochrist.satisfactionsurvey.service.impl;

import com.github.barcochrist.satisfactionsurvey.model.Question;
import com.github.barcochrist.satisfactionsurvey.model.QuestionOption;
import com.github.barcochrist.satisfactionsurvey.repository.QuestionOptionRepository;
import com.github.barcochrist.satisfactionsurvey.repository.QuestionRepository;
import com.github.barcochrist.satisfactionsurvey.service.QuestionService;
import java.util.List;
import java.util.Optional;
import javax.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl implements QuestionService {

  private final QuestionRepository questionRepository;
  private final QuestionOptionRepository questionOptionRepository;

  /**
   * All arguments constructor.
   */
  public QuestionServiceImpl(
      QuestionRepository questionRepository,
      QuestionOptionRepository questionOptionRepository
  ) {
    this.questionRepository = questionRepository;
    this.questionOptionRepository = questionOptionRepository;
  }

  @NotNull
  @Override
  public Optional<Question> findById(String id) {
    return questionRepository.findById(id);
  }

  @NotNull
  @Override
  public List<QuestionOption> findOptionsByQuestionId(String questionId) {
    return questionOptionRepository.findByQuestionId(questionId);
  }

  @NotNull
  @Override
  public Page<Question> findAll(Pageable pageable) {
    return questionRepository.findAll(pageable);
  }
}