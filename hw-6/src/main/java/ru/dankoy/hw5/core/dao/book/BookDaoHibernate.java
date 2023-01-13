package ru.dankoy.hw5.core.dao.book;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.dankoy.hw5.core.domain.Book;


@RequiredArgsConstructor
@Repository
public class BookDaoHibernate implements BookDao {

  @PersistenceContext
  private final EntityManager entityManager;

  @Override
  public List<Book> getAll() {
    // дя решения проблемы n+1 используются решение с графом и join fetch
    var authorEntityGraph = entityManager.getEntityGraph("authors-entity-graph");
    var query = entityManager.createQuery("select b from Book b join fetch b.genres", Book.class);
    query.setHint("javax.persistence.fetchgraph", authorEntityGraph);
    return query.getResultList();
  }

  @Override
  public Optional<Book> getById(long id) {
    return Optional.ofNullable(entityManager.find(Book.class, id));
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
