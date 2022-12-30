package ru.dankoy.hw5.core.service.genre;

import java.util.List;
import ru.dankoy.hw5.core.domain.Genre;

public interface GenreService {

  List<Genre> getAll();

  Genre getById(long id);

  long insert(String genreName);

  void deleteById(long id);

  void update(Genre genre);

  long count();

}
