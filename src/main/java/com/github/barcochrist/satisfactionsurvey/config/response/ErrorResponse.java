package com.github.barcochrist.satisfactionsurvey.config.response;

import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse implements java.io.Serializable {

  @NotNull
  private String message;

  @NotNull
  private LocalDateTime timestamp;

  @NotNull
  private Integer status;

  /**
   * Static factory method.
   *
   * @param message   Error message
   * @param timestamp Current date time
   * @param httpCode  Http Status code
   * @return A new {@link ErrorResponse} instance
   */
  public static ErrorResponse from(
      String message,
      LocalDateTime timestamp,
      Integer httpCode
  ) {
    return new ErrorResponse(message, timestamp, httpCode);
  }
}