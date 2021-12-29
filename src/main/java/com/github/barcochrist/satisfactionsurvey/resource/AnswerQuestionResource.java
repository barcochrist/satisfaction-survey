package com.github.barcochrist.satisfactionsurvey.resource;

import com.github.barcochrist.satisfactionsurvey.model.AnswerQuestion;
import com.github.barcochrist.satisfactionsurvey.model.enums.QuestionType;
import java.util.List;
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
public class AnswerQuestionResource {

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

  @Null
  private QuestionType questionType;

  @Null
  private List<QuestionOptionResource> options;

  /**
   * Factory method.
   *
   * @param other Other {@link AnswerQuestion}
   * @return A new {@link AnswerQuestionResource} instance
   */
  @NotNull
  public static AnswerQuestionResource from(
      @NotNull AnswerQuestion other,
      @Null String response,
      @Null String questionTitle,
      @Null QuestionType questionType,
      @Null List<QuestionOptionResource> options
  ) {
    return new AnswerQuestionResource(
        other.getId(),
        other.getAnswerId(),
        other.getQuestionId(),
        response,
        questionTitle,
        questionType,
        options
    );
  }
}