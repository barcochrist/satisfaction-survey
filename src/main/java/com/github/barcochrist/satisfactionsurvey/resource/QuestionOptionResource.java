package com.github.barcochrist.satisfactionsurvey.resource;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.barcochrist.satisfactionsurvey.model.QuestionOption;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * DTO for response data from {@link QuestionOption} type.
 */
@Data
@AllArgsConstructor
public class QuestionOptionResource implements QuestionOption {

  @NotBlank
  private String id;

  @NotBlank
  @JsonIgnore
  private String questionId;

  @NotBlank
  private String name;

  /**
   * Factory method.
   *
   * @param other Other {@link QuestionOption}
   * @return A new {@link QuestionOptionResource} instance
   */
  @NotNull
  public static QuestionOptionResource from(
      @NotNull QuestionOption other
  ) {
    return new QuestionOptionResource(
        other.getId(),
        other.getQuestionId(),
        other.getName()
    );
  }
}