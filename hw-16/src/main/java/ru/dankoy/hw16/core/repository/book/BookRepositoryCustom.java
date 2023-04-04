package ru.dankoy.hw16.core.repository.book;

import java.util.List;
import ru.dankoy.hw16.core.domain.Book;
import ru.dankoy.hw16.core.domain.Genre;

public interface BookRepositoryCustom {

  List<Genre> getAllGenresByBookId(String bookId);

  Book saveAndCheckAuthors(Book book);

  void deleteByBookId(String bookId);
}
