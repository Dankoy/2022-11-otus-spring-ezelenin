package ru.dankoy.hw5.core.dao.commentary;

import java.util.List;
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
  public List<Commentary> getAllByBookId(long id) {
    return null;
  }

  @Override
  public Optional<Commentary> getById(long id) {
    return Optional.empty();
  }

  @Override
  public Commentary insertOrUpdate(Commentary commentary) {
    return null;
  }

  @Override
  public void delete(Commentary commentary) {

  }

  @Override
  public Commentary update(Commentary commentary) {
    return null;
  }

  @Override
  public long count() {
    return 0;
  }
}
