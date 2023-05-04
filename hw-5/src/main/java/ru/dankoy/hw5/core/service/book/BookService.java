package ru.dankoy.hw5.core.service.book;

import java.util.List;
import ru.dankoy.hw5.core.domain.Book;

public interface BookService {

  List<Book> getAll();

  Book getById(long id);

  long insert(Book book, long[] authorIds, long[] genreIds);

  void deleteById(long id);

  void update(Book book, long[] authorIds, long[] genreIds);

  long count();

}
