package ru.dankoy.hw7.core.repository.book;

import java.util.List;
import java.util.Optional;
import ru.dankoy.hw7.core.domain.Book;

public interface BookRepositoryCustom {

  Optional<Book> getById(long id);

  List<Book> getAllWithBooksAndGenres();
}
