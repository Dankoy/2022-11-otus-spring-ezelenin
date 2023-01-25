package ru.dankoy.hw8.core.repository.commentary;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import ru.dankoy.hw8.core.domain.Commentary;

public interface CommentaryRepository extends CrudRepository<Commentary, Integer>,
    CommentaryRepositoryCustom {

  @Override
  Optional<Commentary> getById(int id);


}
