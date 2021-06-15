/**
 * Description: Application Exception class.
 *
 * @author: Ashwin Padmakumar
 * @since: 2021-06-15
 * @version: 0.1
 */

package com.workspace.urlshortener.exception;

public class ApplicationException extends RuntimeException {
  public ApplicationException(String exception) {
    super(exception);
  }
}
