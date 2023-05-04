package ru.dankoy.hw16.core.service.book;

import java.util.List;
import java.util.Optional;
import ru.dankoy.hw16.core.domain.Author;
import ru.dankoy.hw16.core.domain.Book;
import ru.dankoy.hw16.core.domain.Genre;

public interface BookService {

  List<Book> findAllByGenreName(Genre genre);

  List<Book> findAllByAuthorId(Author author);

  List<Book> findAll();

  Optional<Book> getById(String id);

  Book insertOrUpdate(Book book);

  void deleteById(String id);

  long count();

  void updateMultiple(List<Book> books);
}
