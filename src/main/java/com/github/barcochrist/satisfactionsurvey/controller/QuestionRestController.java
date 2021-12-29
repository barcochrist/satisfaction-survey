package com.github.barcochrist.satisfactionsurvey.controller;

import com.github.barcochrist.satisfactionsurvey.model.Question;
import com.github.barcochrist.satisfactionsurvey.model.enums.QuestionType;
import com.github.barcochrist.satisfactionsurvey.resource.QuestionOptionResource;
import com.github.barcochrist.satisfactionsurvey.resource.QuestionResource;
import com.github.barcochrist.satisfactionsurvey.service.QuestionService;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/questions")
public class QuestionRestController {

  private final QuestionService questionService;

  /**
   * All arguments constructor.
   */
  public QuestionRestController(QuestionService questionService) {
    this.questionService = questionService;
  }

  /**
   * Find {@link Question} by identifier.
   */
  @GetMapping("/{id}")
  public ResponseEntity<QuestionResource> findById(
      @PathVariable(value = "id") String id
  ) {
    var response = questionService
        .findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Question not found"));

    var options =
        QuestionType.MULTIPLE_CHOICE.equals(response.getType())
            ? questionService
            .findOptionsByQuestionId(response.getId())
            .stream()
            .map(QuestionOptionResource::from)
            .collect(Collectors.toList())
            : null;

    return ResponseEntity.ok(QuestionResource.from(response, options));
  }
}