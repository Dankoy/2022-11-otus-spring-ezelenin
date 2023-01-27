package ru.dankoy.hw8.core.service.book;

import java.util.List;
import java.util.Optional;
import ru.dankoy.hw8.core.domain.Book;

public interface BookService {

  List<Book> findAll();

  Optional<Book> getById(String id);

  Book insertOrUpdate(Book book, String[] authorIds, String[] genreIds);

  void deleteById(String id);

  Book update(Book book, String[] authorIds, String[] genreIds);

  long count();

}
