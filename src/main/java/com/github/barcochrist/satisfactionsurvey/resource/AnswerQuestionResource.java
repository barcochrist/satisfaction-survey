package com.github.barcochrist.satisfactionsurvey.resource;

import com.github.barcochrist.satisfactionsurvey.model.AnswerQuestion;
import java.util.Optional;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * DTO for response data from {@link AnswerQuestion} type.
 */
@Data
@AllArgsConstructor
public class AnswerQuestionResource implements AnswerQuestion {

  @NotBlank
  private String id;

  @NotBlank
  private String answerId;

  @NotBlank
  private String questionId;

  @Null
  private String response;

  @Null
  private String questionTitle;

  /**
   * Factory method.
   *
   * @param other Other {@link AnswerQuestion}
   * @return A new {@link AnswerQuestionResource} instance
   */
  @NotNull
  public static AnswerQuestionResource from(
      @NotNull AnswerQuestion other,
      @Null String questionTitle
  ) {
    return new AnswerQuestionResource(
        other.getId(),
        other.getAnswerId(),
        other.getQuestionId(),
        other.getResponse().orElse(null),
        questionTitle
    );
  }

  @NotNull
  @Override
  public Optional<String> getResponse() {
    return Optional.of(response);
  }
}
