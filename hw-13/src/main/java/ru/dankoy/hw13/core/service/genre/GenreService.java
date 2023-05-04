package ru.dankoy.hw13.core.service.genre;

import java.util.List;
import java.util.Optional;
import ru.dankoy.hw13.core.domain.Genre;

public interface GenreService {

  List<Genre> getAll();

  Optional<Genre> getById(long id);

  Genre insertOrUpdate(Genre genre);

  void deleteById(long id);

  long count();

}
