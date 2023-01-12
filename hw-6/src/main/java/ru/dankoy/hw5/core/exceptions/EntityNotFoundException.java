package ru.dankoy.hw5.core.exceptions;

public class EntityNotFoundException extends Hw5RootException {

  public EntityNotFoundException(String message) {
    super(message);
  }

  public EntityNotFoundException() {
    super();
  }

}
