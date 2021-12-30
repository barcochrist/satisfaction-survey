package com.github.barcochrist.satisfactionsurvey.entity;

import com.github.barcochrist.satisfactionsurvey.model.QuestionOption;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * {@link QuestionOption} type Jpa implementation.
 */
@Data
@Entity
@Builder
@AllArgsConstructor
@Table(name = "question_options")
public class QuestionOptionJpa implements QuestionOption {

  @Id
  @NotBlank
  private String id;

  @NotBlank
  @Column(name = "questions_id")
  private String questionId;

  @NotBlank
  private String name;

  /**
   * Private default constructor for JPA.
   */
  private QuestionOptionJpa() {
  }

  /**
   * Factory method.
   *
   * @param other Other {@link QuestionOption}
   * @return A new {@link QuestionOptionJpa} instance
   */
  @NotNull
  public static QuestionOptionJpa from(
      @NotNull QuestionOption other
  ) {
    return new QuestionOptionJpa(
        other.getId(),
        other.getQuestionId(),
        other.getName()
    );
  }
}