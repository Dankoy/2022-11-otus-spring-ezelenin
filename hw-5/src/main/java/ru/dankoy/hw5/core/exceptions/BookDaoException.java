package ru.dankoy.hw5.core.exceptions;

public class BookDaoException extends Hw5RootException {

  public BookDaoException(String message) {
    super(message);
  }

  public BookDaoException(String message, Exception e) {
    super(message, e);
  }
}
