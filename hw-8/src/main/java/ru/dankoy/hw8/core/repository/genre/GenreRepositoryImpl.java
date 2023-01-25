package ru.dankoy.hw8.core.repository.genre;


import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.keyvalue.core.KeyValueOperations;
import ru.dankoy.hw8.core.domain.Genre;

@RequiredArgsConstructor
public class GenreRepositoryImpl implements GenreRepositoryCustom {

  private final KeyValueOperations keyValueOperations;

  @Override
  public Optional<Genre> getById(int id) {
    return keyValueOperations.findById(id, Genre.class);
  }
}
