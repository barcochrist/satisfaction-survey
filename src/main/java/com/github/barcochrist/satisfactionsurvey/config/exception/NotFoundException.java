package com.github.barcochrist.satisfactionsurvey.config.exception;

import lombok.NoArgsConstructor;

/**
 * Class for handle Not Found exceptions.
 */
@NoArgsConstructor
public class NotFoundException extends RuntimeException {

  /**
   * Constructor with message.
   *
   * @param message Exception message
   */
  public NotFoundException(String message) {
    super(message);
  }
}