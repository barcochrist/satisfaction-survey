package com.github.barcochrist.satisfactionsurvey.resource;

import com.github.barcochrist.satisfactionsurvey.model.Answer;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

/**
 * DTO for receive data from {@link Answer} type.
 */
@Data
@Builder
public class AnswerDraftResource {

  @NotBlank
  private String email;

  @NotBlank
  private String customerName;

  @Valid
  @NotNull
  private List<AnswerQuestionDraftResource> answers;
}