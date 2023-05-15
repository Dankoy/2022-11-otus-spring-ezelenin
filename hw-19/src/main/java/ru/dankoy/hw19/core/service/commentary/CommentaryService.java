package ru.dankoy.hw19.core.service.commentary;

import java.util.List;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;
import ru.dankoy.hw19.core.aspects.AddCurrentUser;
import ru.dankoy.hw19.core.domain.Commentary;

public interface CommentaryService {

  List<Commentary> getAllByBookId(String id);

  @Transactional
  void deleteAllByBookId(String bookId);

  Optional<Commentary> getById(String id);

  Commentary update(Commentary commentary);

  @AddCurrentUser
  Commentary insert(Commentary commentary);

  void deleteById(String id);


}
