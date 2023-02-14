package ru.dankoy.hw10.core.exceptions;

public class EntityNotFoundException extends Hw5RootException {

  private static final String MESSAGE = "Entity %s has not been found with id - %d";

  public EntityNotFoundException(String message) {
    super(message);
  }

  public EntityNotFoundException(long id, Entity entity) {
    super(String.format(MESSAGE, entity.getName(), id));
  }

}
