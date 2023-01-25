package ru.dankoy.hw8.core.repository.book;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.keyvalue.core.KeyValueOperations;
import ru.dankoy.hw8.core.domain.Book;

@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepositoryCustom {

  private final KeyValueOperations keyValueOperations;

  @Override
  public Optional<Book> getById(int id) {
    return keyValueOperations.findById(id, Book.class);
  }

  @Override
  public List<Book> getAllWithBooksAndGenres() {
    return (List<Book>) keyValueOperations.findAll(Book.class);
  }
}
