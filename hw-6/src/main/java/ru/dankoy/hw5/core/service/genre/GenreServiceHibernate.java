package ru.dankoy.hw5.core.service.genre;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dankoy.hw5.core.dao.genre.GenreDao;
import ru.dankoy.hw5.core.domain.Genre;
import ru.dankoy.hw5.core.exceptions.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class GenreServiceHibernate implements GenreService {

  private final GenreDao genreDao;

  @Transactional(readOnly = true)
  @Override
  public List<Genre> getAll() {
    return genreDao.getAll();
  }

  @Transactional(readOnly = true)
  @Override
  public Optional<Genre> getById(long id) {
    return genreDao.getById(id);
  }

  @Transactional
  @Override
  public Genre insert(Genre genre) {
    return genreDao.insertOrUpdate(genre);
  }

  @Transactional
  @Override
  public void deleteById(long id) {
    var optional = genreDao.getById(id);
    var genre = optional.orElseThrow(() -> new EntityNotFoundException(
        String.format("Entity %s has not been found with id - %d", Genre.class.getName(), id)));
    genreDao.delete(genre);
  }

  @Transactional
  @Override
  public Genre update(Genre genre) {
    return genreDao.update(genre);
  }

  @Transactional(readOnly = true)
  @Override
  public long count() {
    return genreDao.count();
  }
}
