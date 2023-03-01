package ru.dankoy.hw11.core.service.genre;

import java.util.Set;
import ru.dankoy.hw11.core.domain.Genre;

public interface GenreService {

  void update(Genre oldGenre, Genre newGenre);


  void delete(Genre genre);

  Set<Genre> getAllGenres();
}
