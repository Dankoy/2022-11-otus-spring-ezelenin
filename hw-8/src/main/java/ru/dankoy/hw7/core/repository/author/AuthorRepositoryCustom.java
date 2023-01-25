package ru.dankoy.hw7.core.repository.author;

import java.util.Optional;
import ru.dankoy.hw7.core.domain.Author;

public interface AuthorRepositoryCustom {

  Optional<Author> getById(long id);

}
