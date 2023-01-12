package ru.dankoy.hw5.core.exceptions;

public class Hw5RootException extends RuntimeException {

  public Hw5RootException(String message, Exception e) {
    super(message, e);
  }

  public Hw5RootException(Exception e) {
    super(e);
  }

  public Hw5RootException(String message) {
    super(message);
  }

  public Hw5RootException() {
    super();
  }
}
