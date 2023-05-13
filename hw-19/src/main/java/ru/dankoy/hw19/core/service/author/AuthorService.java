package ru.dankoy.hw19.core.service.author;

import java.util.List;
import java.util.Optional;
import ru.dankoy.hw19.core.domain.Author;

public interface AuthorService {

  List<Author> getAll();

  Optional<Author> getById(String id);

  Author insertOrUpdate(Author author);

  void deleteById(String id);

  long count();

}