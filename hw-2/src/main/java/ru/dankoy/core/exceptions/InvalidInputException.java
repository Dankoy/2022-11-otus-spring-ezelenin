package ru.dankoy.core.exceptions;

public class InvalidInputException extends RuntimeException {

  public InvalidInputException(String message, Exception ex) {
    super(message, ex);
  }

}
