package ru.dankoy.hw7.core.repository.book;

import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import ru.dankoy.hw7.core.domain.Book;

@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepositoryCustom {

  @PersistenceContext
  private final EntityManager entityManager;

  @Override
  public Optional<Book> getById(long id) {
    return Optional.ofNullable(entityManager.find(Book.class, id));
  }
}
