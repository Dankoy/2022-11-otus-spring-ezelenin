package ru.dankoy.hw5.core.service.genre;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dankoy.hw5.core.dao.genre.GenreDao;
import ru.dankoy.hw5.core.domain.Genre;

@Service
@RequiredArgsConstructor
public class GenreServiceJdbc implements GenreService {

  private final GenreDao genreDao;

  @Override
  public List<Genre> getAll() {
    return genreDao.getAll();
  }

  @Override
  public Genre getById(long id) {
    return genreDao.getById(id);
  }

  @Override
  public long insert(String genreName) {
    return genreDao.insert(genreName);
  }

  @Override
  public void deleteById(long id) {
    genreDao.deleteById(id);
  }

  @Override
  public void update(Genre genre) {
    genreDao.getById(genre.getId());
    genreDao.update(genre);
  }

  @Override
  public long count() {
    return genreDao.count();
  }
}
