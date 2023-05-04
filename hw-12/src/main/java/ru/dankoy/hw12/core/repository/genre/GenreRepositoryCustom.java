package ru.dankoy.hw12.core.repository.genre;

import java.util.Optional;
import ru.dankoy.hw12.core.domain.Genre;

public interface GenreRepositoryCustom {

  Optional<Genre> getById(long id);
}
