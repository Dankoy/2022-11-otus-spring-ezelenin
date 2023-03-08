package ru.dankoy.hw12.core.repository.author;

import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import ru.dankoy.hw12.core.domain.Author;


@RequiredArgsConstructor
public class AuthorRepositoryImpl implements AuthorRepositoryCustom {

  @PersistenceContext
  private final EntityManager entityManager;

  @Override
  public Optional<Author> getById(long id) {
    return Optional.ofNullable(entityManager.find(Author.class, id));
  }
}
