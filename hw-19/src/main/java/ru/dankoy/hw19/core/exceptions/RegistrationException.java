package ru.dankoy.hw19.core.exceptions;

public class RegistrationException extends Hw5RootException {

  public RegistrationException(String message, Exception e) {
    super(message, e);
  }

  public RegistrationException(String message) {
    super(message);
  }
}
