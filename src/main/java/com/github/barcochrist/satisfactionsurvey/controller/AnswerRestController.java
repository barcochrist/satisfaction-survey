package com.github.barcochrist.satisfactionsurvey.controller;

import com.github.barcochrist.satisfactionsurvey.model.Answer;
import com.github.barcochrist.satisfactionsurvey.model.Question;
import com.github.barcochrist.satisfactionsurvey.resource.AnswerQuestionResource;
import com.github.barcochrist.satisfactionsurvey.resource.AnswerResource;
import com.github.barcochrist.satisfactionsurvey.service.AnswerService;
import com.github.barcochrist.satisfactionsurvey.service.QuestionService;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/answers")
public class AnswerRestController {

  private final AnswerService answerService;
  private final QuestionService questionService;

  /**
   * All arguments constructor.
   */
  public AnswerRestController(
      AnswerService answerService,
      QuestionService questionService
  ) {
    this.answerService = answerService;
    this.questionService = questionService;
  }

  /**
   * Find all {@link Answer}s.
   */
  @GetMapping
  public ResponseEntity<List<AnswerResource>> findAll(Pageable pageable) {
    Page<AnswerResource> response = answerService
        .findAll(pageable)
        .map(this::instanceAnswerResource);

    return ResponseEntity.ok(response.getContent());
  }

  @NotNull
  private AnswerResource instanceAnswerResource(@NotNull Answer response) {
    var answers = answerService
        .findAnswerQuestionsByAnswerId(response.getId())
        .stream()
        .map(answerQuestion -> {
          var question = questionService.findById(answerQuestion.getQuestionId());
          return AnswerQuestionResource.from(
              answerQuestion,
              question.map(Question::getTitle).orElse(null)
          );
        })
        .collect(Collectors.toList());

    return AnswerResource.from(response, answers);
  }
}