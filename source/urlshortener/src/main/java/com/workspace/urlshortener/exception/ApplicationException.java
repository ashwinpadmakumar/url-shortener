package com.workspace.urlshortener.exception;

import lombok.AllArgsConstructor;

/**
 * Classification: Trimble Confidential.
 * Description: Custom Banner for Startup
 *
 * @author: Ashwin Padmakumar
 * @since: 2021-06-15
 * @version: 0.1
 */
public class ApplicationException extends RuntimeException {
  public ApplicationException(String exception) { super(exception); }
}
