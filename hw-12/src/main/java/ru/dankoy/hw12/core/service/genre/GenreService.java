package ru.dankoy.hw12.core.service.genre;

import java.util.List;
import java.util.Optional;
import ru.dankoy.hw12.core.domain.Genre;

public interface GenreService {

  List<Genre> getAll();

  Optional<Genre> getById(long id);

  Genre insertOrUpdate(Genre genre);

  void deleteById(long id);

  long count();

}
