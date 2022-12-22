package ru.dankoy.core.exceptions;

public class TestingException extends RuntimeException{

  public TestingException(Exception ex) {
    super(ex);
  }

  public TestingException(String message, Exception ex) {
    super(message, ex);
  }

}
