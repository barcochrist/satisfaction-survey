package com.github.barcochrist.satisfactionsurvey.controller;

import com.github.barcochrist.satisfactionsurvey.model.Question;
import com.github.barcochrist.satisfactionsurvey.model.enums.QuestionType;
import com.github.barcochrist.satisfactionsurvey.resource.QuestionOptionResource;
import com.github.barcochrist.satisfactionsurvey.resource.QuestionResource;
import com.github.barcochrist.satisfactionsurvey.service.QuestionService;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    return ResponseEntity.ok(instanceQuestionResource(response));
  }

  /**
   * Find all {@link Question}s.
   */
  @GetMapping
  public ResponseEntity<List<QuestionResource>> findAll(Pageable pageable) {
    Page<QuestionResource> response = questionService
        .findAll(pageable)
        .map(this::instanceQuestionResource);

    return ResponseEntity.ok(response.getContent());
  }

  @NotNull
  private QuestionResource instanceQuestionResource(@NotNull Question response) {
    var options =
        QuestionType.MULTIPLE_CHOICE.equals(response.getType())
            ? questionService
            .findOptionsByQuestionId(response.getId())
            .stream()
            .map(QuestionOptionResource::from)
            .collect(Collectors.toList())
            : null;

    return QuestionResource.from(response, options);
  }
}