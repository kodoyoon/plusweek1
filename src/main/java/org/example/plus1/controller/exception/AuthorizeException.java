package org.example.plus1.controller.exception;

public class AuthorizeException extends RuntimeException {
  public AuthorizeException(String message) {
    super(message);
  }
}
