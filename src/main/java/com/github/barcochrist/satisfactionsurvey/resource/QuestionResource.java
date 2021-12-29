package com.github.barcochrist.satisfactionsurvey.resource;

import com.github.barcochrist.satisfactionsurvey.model.Question;
import com.github.barcochrist.satisfactionsurvey.model.enums.QuestionType;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * DTO for response data from {@link Question} type.
 */
@Data
@AllArgsConstructor
public class QuestionResource implements Question {

  @NotBlank
  private String id;

  @NotBlank
  private String title;

  @NotNull
  private QuestionType type;

  @NotNull
  private Boolean isRequired;

  @Null
  private List<QuestionOptionResource> options;

  /**
   * Factory method.
   *
   * @param other Other {@link Question}
   * @return A new {@link QuestionResource} instance
   */
  @NotNull
  public static QuestionResource from(
      @NotNull Question other,
      @Null List<QuestionOptionResource> options
  ) {
    return new QuestionResource(
        other.getId(),
        other.getTitle(),
        other.getType(),
        other.getIsRequired(),
        options
    );
  }
}