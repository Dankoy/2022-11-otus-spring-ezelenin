package ru.dankoy.hw8.core.repository.author;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.keyvalue.core.KeyValueOperations;
import ru.dankoy.hw8.core.domain.Author;


@RequiredArgsConstructor
public class AuthorRepositoryImpl implements AuthorRepositoryCustom {

  private final KeyValueOperations keyValueTemplate;

  @Override
  public Optional<Author> getById(int id) {
    return keyValueTemplate.findById(id, Author.class);
  }
}
