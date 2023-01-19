package ru.dankoy.hw7.core.repository.book;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.dankoy.hw7.core.domain.Book;


@RequiredArgsConstructor
@Repository
public class BookDaoHibernate implements BookDao {

  @PersistenceContext
  private final EntityManager entityManager;

  @Override
  public List<Book> getAll() {
    // дя решения проблемы n+1 используются решение с графом и join fetch
    // часть списков обновляются в сервисе через метод size
    var authorEntityGraph = entityManager.getEntityGraph("authors-entity-graph");
    var query = entityManager.createQuery("select b from Book b join fetch b.genres", Book.class);
    query.setHint("javax.persistence.fetchgraph", authorEntityGraph);
    return query.getResultList();
  }

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
  public Book insertOrUpdate(Book book) {
    if (book.getId() == 0) {
      entityManager.persist(book);
      return book;
    } else {
      return entityManager.merge(book);
    }
  }

  @Override
  public void delete(Book book) {
    entityManager.remove(book);
  }

  @Override
  public Book update(Book book) {
    return entityManager.merge(book);
  }

  @Override
  public long count() {
    return getAll().size();
  }
}
