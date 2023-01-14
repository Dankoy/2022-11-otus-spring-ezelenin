package ru.dankoy.hw5.core.service.author;

import java.util.List;
import java.util.Optional;
import ru.dankoy.hw5.core.domain.Author;

public interface AuthorService {

  List<Author> getAll();

  Optional<Author> getById(long id);

  Author insertOrUpdate(Author author);

  void deleteById(long id);

  long count();

}
