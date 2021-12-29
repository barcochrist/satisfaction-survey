package com.github.barcochrist.satisfactionsurvey.entity;

import com.github.barcochrist.satisfactionsurvey.model.Answer;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * {@link Answer} type Jpa implementation.
 */
@Data
@Entity
@AllArgsConstructor
@Table(name = "answers")
public class AnswerJpa implements Answer {

  @Id
  @NotBlank
  private String id;

  @NotBlank
  private String email;

  @NotBlank
  private String customerName;

  /**
   * Private default constructor for JPA.
   */
  private AnswerJpa() {
  }

  /**
   * Factory method.
   *
   * @param other Other {@link Answer}
   * @return A new {@link AnswerJpa} instance
   */
  @NotNull
  public static AnswerJpa from(
      @NotNull Answer other
  ) {
    return new AnswerJpa(
        other.getId(),
        other.getEmail(),
        other.getCustomerName()
    );
  }
}
