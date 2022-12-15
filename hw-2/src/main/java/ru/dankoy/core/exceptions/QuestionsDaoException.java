package ru.dankoy.core.exceptions;

public class QuestionsDaoException extends RuntimeException {

  public QuestionsDaoException(Exception ex) {
    super(ex);
  }

}
