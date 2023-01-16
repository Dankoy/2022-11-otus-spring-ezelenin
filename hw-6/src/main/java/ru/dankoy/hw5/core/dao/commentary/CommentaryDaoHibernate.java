package ru.dankoy.hw5.core.dao.commentary;

import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.dankoy.hw5.core.domain.Commentary;


@RequiredArgsConstructor
@Repository
public class CommentaryDaoHibernate implements CommentaryDao {

  @PersistenceContext
  private final EntityManager entityManager;

  @Override
  public Optional<Commentary> getById(long id) {
    return Optional.ofNullable(entityManager.find(Commentary.class, id));
  }

  @Override
  public Commentary insertOrUpdate(Commentary commentary) {
    if (commentary.getId() == 0) {
      entityManager.persist(commentary);
      return commentary;
    } else {
      return entityManager.merge(commentary);
    }
  }

  @Override
  public void delete(Commentary commentary) {
    entityManager.remove(commentary);
  }

}
