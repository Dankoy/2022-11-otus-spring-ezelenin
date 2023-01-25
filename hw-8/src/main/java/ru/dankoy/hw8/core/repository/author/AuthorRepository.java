package ru.dankoy.hw8.core.repository.author;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import ru.dankoy.hw8.core.domain.Author;

public interface AuthorRepository extends CrudRepository<Author, Integer>, AuthorRepositoryCustom {

  @Override
  Optional<Author> getById(int id);


}
