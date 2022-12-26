package ru.dankoy.hw5.core.dao.book;

import java.util.List;
import ru.dankoy.hw5.core.domain.Book;

public interface BookDao {

  List<Book> getAll();

  Book getById(long id);

  long insert(Book book);

  void deleteById(long id);

  Book update(Book book);

  long count();

}
