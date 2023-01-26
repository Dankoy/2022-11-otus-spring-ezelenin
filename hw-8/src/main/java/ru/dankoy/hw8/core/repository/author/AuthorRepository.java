package ru.dankoy.hw8.core.repository.author;

import java.util.Optional;
import org.springframework.data.keyvalue.repository.KeyValueRepository;
import ru.dankoy.hw8.core.domain.Author;

public interface AuthorRepository extends KeyValueRepository<Author, String>,
    AuthorRepositoryCustom {

  @Override
  Optional<Author> getById(String id);


}
