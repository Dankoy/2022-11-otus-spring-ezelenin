package ru.dankoy.hw8.core.service.book;

import java.util.List;
import java.util.Optional;
import ru.dankoy.hw8.core.domain.Author;
import ru.dankoy.hw8.core.domain.Book;
import ru.dankoy.hw8.core.domain.Genre;

public interface BookService {

  List<Book> findAllByGenreName(Genre genre);

  List<Book> findAllByAuthorId(Author author);

  List<Book> findAll();

  Optional<Book> getById(String id);

  Book insertOrUpdate(Book book, String[] authorIds, String[] genreIds);

  void deleteById(String id);

  Book update(Book book, String[] authorIds, String[] genreIds);

  long count();

  void updateMultiple(List<Book> books);
}
