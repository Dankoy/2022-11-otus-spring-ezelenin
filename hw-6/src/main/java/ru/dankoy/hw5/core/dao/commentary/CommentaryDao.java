package ru.dankoy.hw5.core.dao.commentary;

import java.util.List;
import java.util.Optional;
import ru.dankoy.hw5.core.domain.Commentary;

public interface CommentaryDao {

  List<Commentary> getAllByBookId(long id);

  Optional<Commentary> getById(long id);

  Commentary insertOrUpdate(Commentary commentary);

  void delete(Commentary commentary);

  Commentary update(Commentary commentary);

  long count();

}
