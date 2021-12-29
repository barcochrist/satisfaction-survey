package com.github.barcochrist.satisfactionsurvey.resource;

import com.github.barcochrist.satisfactionsurvey.model.AnswerQuestion;
import java.util.List;
import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 * DTO for receive data from {@link AnswerQuestion} type.
 */
@Data
public class AnswerQuestionDraftResource {

  @NotBlank
  private String questionId;

  private String response;

  private List<String> optionIds;
}