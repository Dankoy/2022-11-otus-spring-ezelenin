package ru.dankoy.hw5.core.dao.commentary;

import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.dankoy.hw5.core.domain.Commentary;

// @Repository. Есть мнение что мы этим помечаем что класс хранилище.
// Основная претензия к этому мнению - если хорошо описан интерфейс - то мы в класс и не заглянем,
// а тем более в его аннотации. Мы по имени класса увидим что это репозиторий. И всё,
// функциональность по трансляции исключений становится просто обузой.

@Component
@RequiredArgsConstructor
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
