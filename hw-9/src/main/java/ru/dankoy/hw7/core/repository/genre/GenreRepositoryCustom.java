package ru.dankoy.hw7.core.repository.genre;

import java.util.Optional;
import ru.dankoy.hw7.core.domain.Genre;

public interface GenreRepositoryCustom {

  Optional<Genre> getById(long id);
}
