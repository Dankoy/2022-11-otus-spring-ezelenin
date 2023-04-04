package ru.dankoy.hw16.core.repository.book;

import java.util.List;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.dankoy.hw16.core.domain.Book;
import ru.dankoy.hw16.core.domain.Genre;

public interface BookRepositoryCustom {


  @RestResource(path = "genres-by-book", rel = "genres-by-book")
  List<Genre> getAllGenresByBookId(String bookId);

  @RestResource(path = "save-book", rel = "save-book")
  Book saveAndCheckAuthors(Book book);

  @RestResource(path = "delete-book", rel = "delete-book")
  void deleteByBookId(String bookId);
}
