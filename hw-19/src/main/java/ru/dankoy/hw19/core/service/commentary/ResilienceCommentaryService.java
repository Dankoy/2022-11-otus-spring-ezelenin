package ru.dankoy.hw19.core.service.commentary;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import org.springframework.transaction.annotation.Transactional;
import ru.dankoy.hw19.core.domain.Commentary;

public interface ResilienceCommentaryService {

  CompletableFuture<List<Commentary>> getAllByBookId(String id);

  @Transactional
  void deleteAllByBookId(String bookId);

  CompletableFuture<Optional<Commentary>> getById(String id);

  CompletableFuture<Commentary> insertOrUpdate(Commentary commentary);

  void deleteById(String id);


}
