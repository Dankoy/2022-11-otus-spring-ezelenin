package ru.dankoy.hw7.core.repository.book;

import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import ru.dankoy.hw7.core.domain.Book;

@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepositoryCustom {

  @PersistenceContext
  private final EntityManager entityManager;

  @Override
  public Optional<Book> getById(long id) {
    var authorEntityGraph = entityManager.getEntityGraph("authors-entity-graph");
    var query = entityManager.createQuery(
        "select b from Book b join fetch b.genres where b.id = :id",
        Book.class);
    query.setParameter("id", id);
    query.setHint("javax.persistence.fetchgraph", authorEntityGraph);
    try {
      return Optional.of(query.getSingleResult());
    } catch (NoResultException e) {
      return Optional.empty();
    }
  }
}
