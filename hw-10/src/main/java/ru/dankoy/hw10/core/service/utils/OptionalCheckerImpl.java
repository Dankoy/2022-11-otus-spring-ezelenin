package ru.dankoy.hw10.core.service.utils;

import java.util.Optional;
import org.springframework.stereotype.Service;
import ru.dankoy.hw10.core.exceptions.EntityNotFoundException;

@Service
public class OptionalCheckerImpl implements OptionalChecker {

  @Override
  public <T> T getFromOptionalOrThrowException(Class<T> clazz, Optional<T> optional, String id) {
    return optional.orElseThrow(() -> new EntityNotFoundException(
        String.format("Entity %s has not been found with id - %s", clazz.getName(),
            id)));
  }
}
