package ru.dankoy.hw13.core.repository.genre;

import java.util.Optional;
import ru.dankoy.hw13.core.domain.Genre;

public interface GenreRepositoryCustom {

  Optional<Genre> getById(long id);
}
