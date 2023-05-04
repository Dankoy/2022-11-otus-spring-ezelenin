package ru.dankoy.hw5.core.dao.author;

import java.util.List;
import ru.dankoy.hw5.core.domain.Author;

public interface AuthorDao {

  List<Author> getAll();

  Author getById(long id);

  long insert(String authorName);

  void deleteById(long id);

  void update(Author author);

  long count();

}
