package com.github.barcochrist.satisfactionsurvey.entity;

import com.github.barcochrist.satisfactionsurvey.model.AnswerQuestion;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * {@link AnswerQuestion} type Jpa implementation.
 */
@Data
@Entity
@AllArgsConstructor
@Table(name = "answer_questions")
public class AnswerQuestionJpa implements AnswerQuestion {

  @Id
  @NotBlank
  private String id;

  @NotBlank
  @Column(name = "answers_id")
  private String answerId;

  @NotBlank
  @Column(name = "questions_id")
  private String questionId;

  @NotBlank
  private String response;

  /**
   * Private default constructor for JPA.
   */
  private AnswerQuestionJpa() {
  }

  /**
   * Factory method.
   *
   * @param other Other {@link AnswerQuestion}
   * @return A new {@link AnswerQuestionJpa} instance
   */
  @NotNull
  public static AnswerQuestionJpa from(
      @NotNull AnswerQuestion other
  ) {
    return new AnswerQuestionJpa(
        other.getId(),
        other.getAnswerId(),
        other.getQuestionId(),
        other.getResponse()
    );
  }
}