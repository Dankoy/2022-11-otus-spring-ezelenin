package ru.dankoy.hw5.core.repository.book;

import java.util.List;
import java.util.Optional;
import ru.dankoy.hw5.core.domain.Book;

public interface BookDao {

  List<Book> getAll();

  Optional<Book> getById(long id);

  Book insertOrUpdate(Book book);

  void delete(Book book);

  Book update(Book book);

  long count();

}
