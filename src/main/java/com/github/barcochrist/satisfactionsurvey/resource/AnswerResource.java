package com.github.barcochrist.satisfactionsurvey.resource;

import com.github.barcochrist.satisfactionsurvey.model.Answer;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * DTO for response data from {@link Answer} type.
 */
@Data
@AllArgsConstructor
public class AnswerResource implements Answer {

  @NotBlank
  private String id;

  @NotBlank
  private String email;

  @NotBlank
  private String customerName;

  @NotNull
  private List<AnswerQuestionResource> answers;

  /**
   * Factory method.
   *
   * @param other Other {@link Answer}
   * @return A new {@link AnswerResource} instance
   */
  @NotNull
  public static AnswerResource from(
      @NotNull Answer other,
      @NotNull List<AnswerQuestionResource> answers
  ) {
    return new AnswerResource(
        other.getId(),
        other.getEmail(),
        other.getCustomerName(),
        answers
    );
  }
}