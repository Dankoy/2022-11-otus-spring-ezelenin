package ru.dankoy.hw8.core.repository.book;

import java.util.List;
import ru.dankoy.hw8.core.domain.Book;
import ru.dankoy.hw8.core.domain.Genre;

public interface BookRepositoryCustom {

  List<Genre> getAllGenresByBookId(String bookId);

  Book saveAndCheckAuthors(Book book);
}
