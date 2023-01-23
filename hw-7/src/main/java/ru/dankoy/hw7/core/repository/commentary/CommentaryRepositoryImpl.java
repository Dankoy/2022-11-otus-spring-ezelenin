package ru.dankoy.hw7.core.repository.commentary;

import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import ru.dankoy.hw7.core.domain.Commentary;

@RequiredArgsConstructor
public class CommentaryRepositoryImpl implements CommentaryRepositoryCustom {

  @PersistenceContext
  private final EntityManager entityManager;

  @Override
  public Optional<Commentary> getById(long id) {
    return Optional.ofNullable(entityManager.find(Commentary.class, id));
  }
}
