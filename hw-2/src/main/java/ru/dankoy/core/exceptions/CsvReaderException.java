package ru.dankoy.core.exceptions;

public class CsvReaderException extends RuntimeException {

  public CsvReaderException(Exception ex) {
    super(ex);
  }

}
