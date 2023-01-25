package ru.dankoy.hw8.core.service.commentary;

import java.util.List;
import java.util.Optional;
import ru.dankoy.hw8.core.domain.Commentary;

public interface CommentaryService {

  List<Commentary> getAllByBookId(long id);

  Optional<Commentary> getById(long id);

  Commentary insertOrUpdate(Commentary commentary);

  void deleteById(long id);


}
