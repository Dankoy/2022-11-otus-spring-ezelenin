package ru.dankoy.hw5.core.dao.author;

import java.util.List;
import java.util.Optional;
import ru.dankoy.hw5.core.domain.Author;

public interface AuthorDao {

  List<Author> getAll();

  Optional<Author> getById(long id);

  Author insertOrUpdate(Author author);

  void delete(Author author);

  long count();

}
