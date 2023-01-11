package ru.dankoy.hw5.core.dao.genre;

import java.util.List;
import ru.dankoy.hw5.core.domain.Genre;

public interface GenreDao {

  List<Genre> getAll();

  Genre getById(long id);

  long insert(String genreName);

  void deleteById(long id);

  void update(Genre genre);

  long count();

}
