package ru.dankoy.hw13.core.repository.author;

import java.util.Optional;
import ru.dankoy.hw13.core.domain.Author;

public interface AuthorRepositoryCustom {

  Optional<Author> getById(long id);

}
