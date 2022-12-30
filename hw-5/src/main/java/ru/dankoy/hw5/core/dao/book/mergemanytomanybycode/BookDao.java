package ru.dankoy.hw5.core.dao.book.mergemanytomanybycode;

import java.util.List;
import ru.dankoy.hw5.core.domain.Book;

public interface BookDao {

  List<Book> getAll();

  Book getById(long id);

  long insert(Book book, long[] authorIds, long[] genreIds);

  void deleteById(long id);

  void update(Book book, long[] authorIds, long[] genreIds);

  long count();

}
