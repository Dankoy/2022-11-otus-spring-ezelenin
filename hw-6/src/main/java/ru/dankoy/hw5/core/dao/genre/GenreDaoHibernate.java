package ru.dankoy.hw5.core.dao.genre;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.dankoy.hw5.core.domain.Genre;


@Repository
@RequiredArgsConstructor
public class GenreDaoHibernate implements GenreDao {


  @PersistenceContext
  private final EntityManager entityManager;

  @Override
  public List<Genre> getAll() {
    var query = entityManager.createQuery("select g from Genre g", Genre.class);
    return query.getResultList();
  }

  @Override
  public Optional<Genre> getById(long id) {
    return Optional.ofNullable(entityManager.find(Genre.class, id));
  }

  @Override
  public Genre insertOrUpdate(Genre genre) {
    if (genre.getId() == 0) {
      entityManager.persist(genre);
      return genre;
    } else {
      return entityManager.merge(genre);
    }
  }

  @Override
  public void delete(Genre genre) {
    entityManager.remove(genre);
  }

  @Override
  public Genre update(Genre genre) {
    return entityManager.merge(genre);
  }

  @Override
  public long count() {
    return getAll().size();
  }

}
