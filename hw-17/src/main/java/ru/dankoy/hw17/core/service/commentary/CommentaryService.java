package ru.dankoy.hw17.core.service.commentary;

import java.util.List;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;
import ru.dankoy.hw17.core.domain.Commentary;

public interface CommentaryService {

  List<Commentary> getAllByBookId(String id);

  @Transactional
  void deleteAllByBookId(String bookId);

  Optional<Commentary> getById(String id);

  Commentary insertOrUpdate(Commentary commentary);

  void deleteById(String id);


}
