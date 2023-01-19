package ru.dankoy.hw5.core.repository.genre;

import java.util.List;
import java.util.Optional;
import ru.dankoy.hw5.core.domain.Genre;

public interface GenreDao {

  List<Genre> getAll();

  Optional<Genre> getById(long id);

  Genre insertOrUpdate(Genre genre);

  void delete(Genre genre);

  long count();

}
