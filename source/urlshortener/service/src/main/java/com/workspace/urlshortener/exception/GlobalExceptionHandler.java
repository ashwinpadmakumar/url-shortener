/**
 * Description: Custom Banner for Startup.
 *
 * @author: Ashwin Padmakumar
 * @since: 18/07/21
 * @version: 0.1
 */

package com.workspace.urlshortener.exception;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(ApplicationException.class)
  public ResponseEntity<ApplicationError> handleApplicationException(ApplicationException exception) {
    var error = new ApplicationError(
        exception.getStatusCode().value(),
        exception.getStatusMessage());
    return new ResponseEntity<>(error, exception.getStatusCode());
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<ApplicationError> handleBeanException(ConstraintViolationException exception) {
    var error = new ApplicationError(
        HttpStatus.BAD_REQUEST.value(),
        exception.getLocalizedMessage());
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

}