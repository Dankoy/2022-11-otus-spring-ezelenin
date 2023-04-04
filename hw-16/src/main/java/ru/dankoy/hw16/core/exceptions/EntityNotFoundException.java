package ru.dankoy.hw16.core.exceptions;

public class EntityNotFoundException extends Hw5RootException {

  private static final String MESSAGE = "Entity %s has not been found with id - %d";

  public EntityNotFoundException(String message) {
    super(message);
  }

  public EntityNotFoundException(long id, LibraryElement libraryElement) {
    super(String.format(MESSAGE, libraryElement.getName(), id));
  }

}
