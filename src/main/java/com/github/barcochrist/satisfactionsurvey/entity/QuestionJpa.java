package com.github.barcochrist.satisfactionsurvey.entity;

import com.github.barcochrist.satisfactionsurvey.model.Question;
import com.github.barcochrist.satisfactionsurvey.model.enums.QuestionType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * {@link Question} type Jpa implementation.
 */
@Data
@Entity
@AllArgsConstructor
@Table(name = "questions")
public class QuestionJpa implements Question {

  @Id
  @NotBlank
  private String id;

  @NotBlank
  private String title;

  @NotNull
  @Enumerated(EnumType.STRING)
  private QuestionType type;

  @NotNull
  private Boolean isRequired;

  /**
   * Private default constructor for JPA.
   */
  private QuestionJpa() {
  }

  /**
   * Factory method.
   *
   * @param other Other {@link Question}
   * @return A new {@link QuestionJpa} instance
   */
  @NotNull
  public static QuestionJpa from(
      @NotNull Question other
  ) {
    return new QuestionJpa(
        other.getId(),
        other.getTitle(),
        other.getType(),
        other.getIsRequired()
    );
  }
}