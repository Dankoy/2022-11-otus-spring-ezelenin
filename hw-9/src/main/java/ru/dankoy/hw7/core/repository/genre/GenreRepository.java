package ru.dankoy.hw7.core.repository.genre;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.dankoy.hw7.core.domain.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long>, GenreRepositoryCustom {

  Optional<Genre> getById(long id);

}
