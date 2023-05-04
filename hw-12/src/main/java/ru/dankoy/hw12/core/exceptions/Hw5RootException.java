package ru.dankoy.hw12.core.exceptions;

public class Hw5RootException extends RuntimeException {

  public Hw5RootException(String message, Exception e) {
    super(message, e);
  }

  public Hw5RootException(String message) {
    super(message);
  }

}
