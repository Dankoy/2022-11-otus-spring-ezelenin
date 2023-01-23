package ru.dankoy.hw7.core.repository.book;

import java.util.List;
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

  @Override
  public List<Book> getAllWithBooksAndGenres() {
    // используется для того, что бы сохранить пример с использованием графов и join fetch.
    // EAGER для many-to-many использовать не хочется.

    // для решения проблемы n+1 используются решение с графом и join fetch
    // часть списков обновляются в сервисе через метод size
    var authorEntityGraph = entityManager.getEntityGraph("authors-entity-graph");
    var query = entityManager.createQuery("select b from Book b join fetch b.genres", Book.class);
    query.setHint("javax.persistence.fetchgraph", authorEntityGraph);
    return query.getResultList();
  }
}
