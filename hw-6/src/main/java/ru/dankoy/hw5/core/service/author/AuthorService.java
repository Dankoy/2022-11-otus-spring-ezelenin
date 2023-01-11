package ru.dankoy.hw5.core.service.author;

import java.util.List;
import ru.dankoy.hw5.core.domain.Author;

public interface AuthorService {

  List<Author> getAll();

  Author getById(long id);

  long insert(String authorName);

  void deleteById(long id);

  void update(Author author);

  long count();

}
