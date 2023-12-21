package org.example.plus1.controller.exception;

public class BookNotFoundException extends RuntimeException{

  public BookNotFoundException(String message) {
    super(message);
  }
}
