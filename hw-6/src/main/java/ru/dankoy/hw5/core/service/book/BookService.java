package ru.dankoy.hw5.core.service.book;

import java.util.List;
import java.util.Optional;
import ru.dankoy.hw5.core.domain.Book;

public interface BookService {

  List<Book> getAll();

  Optional<Book> getById(long id);

  Book insert(Book book, long[] authorIds, long[] genreIds);

  void deleteById(long id);

  Book update(Book book, long[] authorIds, long[] genreIds);

  long count();

}
