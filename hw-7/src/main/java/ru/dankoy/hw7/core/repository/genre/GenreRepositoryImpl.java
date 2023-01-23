package ru.dankoy.hw7.core.repository.genre;


import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import ru.dankoy.hw7.core.domain.Genre;

@RequiredArgsConstructor
public class GenreRepositoryImpl implements GenreRepositoryCustom {

  @PersistenceContext
  private final EntityManager entityManager;

  @Override
  public Optional<Genre> getById(long id) {
    return Optional.ofNullable(entityManager.find(Genre.class, id));
  }
}
