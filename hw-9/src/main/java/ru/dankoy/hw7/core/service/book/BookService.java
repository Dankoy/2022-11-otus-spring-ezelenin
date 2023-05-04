package ru.dankoy.hw7.core.service.book;

import java.util.List;
import java.util.Optional;
import ru.dankoy.hw7.core.domain.Book;

public interface BookService {

  List<Book> getAll();

  Optional<Book> getById(long id);

  Book insertOrUpdate(Book book);

  void deleteById(long id);

  Book update(Book book);

  long count();

}
