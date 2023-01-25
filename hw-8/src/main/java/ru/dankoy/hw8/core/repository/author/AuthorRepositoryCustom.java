package ru.dankoy.hw8.core.repository.author;

import java.util.Optional;
import ru.dankoy.hw8.core.domain.Author;

public interface AuthorRepositoryCustom {

  Optional<Author> getById(String id);

}
