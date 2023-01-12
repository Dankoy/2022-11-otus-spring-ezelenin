package ru.dankoy.hw5.core.service.author;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dankoy.hw5.core.dao.author.AuthorDao;
import ru.dankoy.hw5.core.domain.Author;
import ru.dankoy.hw5.core.exceptions.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class AuthorServiceHibernate implements AuthorService {

  private final AuthorDao authorDao;

  @Transactional(readOnly = true)
  @Override
  public List<Author> getAll() {
    return authorDao.getAll();
  }

  @Transactional(readOnly = true)
  @Override
  public Optional<Author> getById(long id) {
    return authorDao.getById(id);
  }

  @Transactional
  @Override
  public Author insert(Author author) {
    return authorDao.insertOrUpdate(author);
  }

  @Transactional
  @Override
  public void deleteById(long id) {
    var optional = authorDao.getById(id);
    var author = optional.orElseThrow(() -> new EntityNotFoundException(
        String.format("Entity %s has not been found with id - %d", Author.class.getName(), id)));
    authorDao.delete(author);
  }

  @Transactional
  @Override
  public void update(Author author) {
    authorDao.update(author);
  }

  @Transactional(readOnly = true)
  @Override
  public long count() {
    return authorDao.count();
  }
}
