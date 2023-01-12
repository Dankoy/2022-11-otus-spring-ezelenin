package ru.dankoy.hw5.core.dao.author;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.dankoy.hw5.core.domain.Author;


@Repository
@AllArgsConstructor
public class AuthorDaoHibernate implements AuthorDao {

  @PersistenceContext
  private final EntityManager entityManager;

  @Override
  public List<Author> getAll() {
    var query = entityManager.createQuery("select a from Author a", Author.class);
    return query.getResultList();
  }

  @Override
  public Optional<Author> getById(long id) {
    return Optional.ofNullable(entityManager.find(Author.class, id));
  }

  @Override
  public Author insertOrUpdate(Author author) {
    if (author.getId() == 0) {
      entityManager.persist(author);
      return author;
    } else {
      return entityManager.merge(author);
    }
  }

  @Override
  public void delete(Author author) {
    entityManager.remove(author);
  }

  @Override
  public Author update(Author author) {
    return entityManager.merge(author);
  }

  @Override
  public long count() {
    return getAll().size();
  }

}
