package ru.dankoy.hw5.core.repository.genre;

import java.util.Optional;
import ru.dankoy.hw5.core.domain.Genre;

public interface GenreRepositoryCustom {

  Optional<Genre> getById(long id);
}
