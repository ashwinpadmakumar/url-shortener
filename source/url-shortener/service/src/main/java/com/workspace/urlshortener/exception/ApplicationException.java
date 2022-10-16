/**
 * Description: Application Exception class.
 *
 * @author: Ashwin Padmakumar
 * @since: 2021-06-15
 * @version: 0.1
 */

package com.workspace.urlshortener.exception;

import org.springframework.http.HttpStatus;

public class ApplicationException extends RuntimeException {
  private final HttpStatus statusCode;
  private final String statusMessage;

  public ApplicationException(HttpStatus statusCode, String statusMessage) {
    this.statusCode = statusCode;
    this.statusMessage = statusMessage;
  }

  public HttpStatus getStatusCode() {
    return statusCode;
  }

  public String getStatusMessage() {
    return statusMessage;
  }
}
