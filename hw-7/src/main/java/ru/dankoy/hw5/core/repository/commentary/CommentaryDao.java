package ru.dankoy.hw5.core.repository.commentary;

import java.util.Optional;
import ru.dankoy.hw5.core.domain.Commentary;

public interface CommentaryDao {

  Optional<Commentary> getById(long id);

  Commentary insertOrUpdate(Commentary commentary);

  void delete(Commentary commentary);

}
