package ru.dankoy.core.exceptions;

public class InvalidInputException extends TestingException {

  public InvalidInputException(String message, Exception ex) {
    super(message, ex);
  }

}
