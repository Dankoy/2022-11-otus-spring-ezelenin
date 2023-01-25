package ru.dankoy.hw8.core.repository.book;

import java.util.List;
import java.util.Optional;
import ru.dankoy.hw8.core.domain.Book;

public interface BookRepositoryCustom {

  Optional<Book> getById(int id);

  List<Book> getAllWithBooksAndGenres();
}
