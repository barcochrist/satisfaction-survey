package com.github.barcochrist.satisfactionsurvey.controller;

import com.github.barcochrist.satisfactionsurvey.model.Answer;
import com.github.barcochrist.satisfactionsurvey.resource.AnswerResource;
import com.github.barcochrist.satisfactionsurvey.service.AnswerService;
import java.util.List;
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

  /**
   * All arguments constructor.
   */
  public AnswerRestController(AnswerService answerService) {
    this.answerService = answerService;
  }

  /**
   * Find all {@link Answer}s.
   */
  @GetMapping
  public ResponseEntity<List<AnswerResource>> findAll(Pageable pageable) {
    Page<AnswerResource> response = answerService
        .findAll(pageable)
        .map(AnswerResource::from);

    return ResponseEntity.ok(response.getContent());
  }
}