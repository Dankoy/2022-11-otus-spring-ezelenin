package ru.dankoy.hw12.core.repository.author;

import java.util.Optional;
import ru.dankoy.hw12.core.domain.Author;

public interface AuthorRepositoryCustom {

  Optional<Author> getById(long id);

}
