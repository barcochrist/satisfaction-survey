package com.github.barcochrist.satisfactionsurvey.config.response;

import com.github.barcochrist.satisfactionsurvey.config.exception.NotFoundException;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestControllerExceptionHandler {

  /**
   * Handle any {@link Exception} ({@link IllegalArgumentException}).
   *
   * @param exception Any exception
   * @return A new instance {@link ErrorResponse} (error formatted)
   */
  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorResponse handleAnyError(Exception exception) {
    return ErrorResponse.from(
        exception.getMessage(),
        LocalDateTime.now(),
        HttpStatus.INTERNAL_SERVER_ERROR.value()
    );
  }

  /**
   * Handle {@link NotFoundException}.
   *
   * @param exception Other {@link NotFoundException} instance
   * @return A new instance {@link ErrorResponse} (error formatted)
   */
  @ExceptionHandler(NotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ErrorResponse handleNotFound(NotFoundException exception) {
    return ErrorResponse.from(
        exception.getMessage(),
        LocalDateTime.now(),
        HttpStatus.NOT_FOUND.value()
    );
  }
}