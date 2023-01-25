package ru.dankoy.hw8.core.repository.genre;

import java.util.Optional;
import ru.dankoy.hw8.core.domain.Genre;

public interface GenreRepositoryCustom {

  Optional<Genre> getById(long id);
}
