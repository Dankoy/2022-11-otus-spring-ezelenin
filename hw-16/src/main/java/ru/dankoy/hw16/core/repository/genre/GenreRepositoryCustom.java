package ru.dankoy.hw16.core.repository.genre;

import java.util.Optional;
import ru.dankoy.hw16.core.domain.Genre;

public interface GenreRepositoryCustom {

  Optional<Genre> getById(long id);
}
