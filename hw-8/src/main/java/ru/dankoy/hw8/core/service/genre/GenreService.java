package ru.dankoy.hw8.core.service.genre;

import java.util.List;
import java.util.Optional;
import ru.dankoy.hw8.core.domain.Genre;

public interface GenreService {

  List<Genre> getAll();

  Optional<Genre> getById(String id);

  Genre insertOrUpdate(Genre genre);

  void deleteById(String id);

  long count();

}
