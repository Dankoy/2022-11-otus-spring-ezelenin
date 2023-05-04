package ru.dankoy.hw12.core.repository.author;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.dankoy.hw12.core.domain.Author;

public interface AuthorRepository extends JpaRepository<Author, Long>, AuthorRepositoryCustom {

  @Override
  Optional<Author> getById(long id);


}
