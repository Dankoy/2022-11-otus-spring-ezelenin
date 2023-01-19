package ru.dankoy.hw5.core.repository.author;

import java.util.Optional;
import ru.dankoy.hw5.core.domain.Author;

public interface AuthorRepositoryCustom {

  Optional<Author> getById(long id);

}
