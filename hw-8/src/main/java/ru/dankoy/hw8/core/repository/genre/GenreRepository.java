package ru.dankoy.hw8.core.repository.genre;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import ru.dankoy.hw8.core.domain.Genre;

public interface GenreRepository extends CrudRepository<Genre, String> {

}
