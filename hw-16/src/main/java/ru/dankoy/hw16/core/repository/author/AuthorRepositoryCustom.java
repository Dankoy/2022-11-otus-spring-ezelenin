package ru.dankoy.hw16.core.repository.author;

import java.util.Optional;
import ru.dankoy.hw16.core.domain.Author;

public interface AuthorRepositoryCustom {

  Optional<Author> getById(long id);

}
