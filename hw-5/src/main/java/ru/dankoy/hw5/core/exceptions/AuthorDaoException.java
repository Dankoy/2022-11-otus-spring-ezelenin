package ru.dankoy.hw5.core.exceptions;

public class AuthorDaoException extends Hw5RootException {

  public AuthorDaoException(String message) {
    super(message);
  }

  public AuthorDaoException(String message, Exception e) {
    super(message, e);
  }
}
