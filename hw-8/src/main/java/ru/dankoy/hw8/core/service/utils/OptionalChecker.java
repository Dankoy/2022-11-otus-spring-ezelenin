package ru.dankoy.hw8.core.service.utils;

import java.util.Optional;

public interface OptionalChecker {

  <T> T getFromOptionalOrThrowException(Class<T> clazz, Optional<T> optional, String id);
}
