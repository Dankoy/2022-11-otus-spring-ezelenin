package ru.dankoy.hw10.core.repository.book;

import java.util.List;
import ru.dankoy.hw10.core.domain.Book;
import ru.dankoy.hw10.core.domain.Genre;

public interface BookRepositoryCustom {

  List<Genre> getAllGenresByBookId(String bookId);

  Book saveAndCheckAuthors(Book book);
}
