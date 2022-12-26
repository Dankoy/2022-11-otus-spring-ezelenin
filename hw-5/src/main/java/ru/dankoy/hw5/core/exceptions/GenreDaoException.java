package ru.dankoy.hw5.core.exceptions;

public class GenreDaoException extends Hw5RootException {

  public GenreDaoException(String message) {
    super(message);
  }

  public GenreDaoException(String message, Exception e) {
    super(message, e);
  }
}
