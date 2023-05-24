package ru.dankoy.hw19.core.service.work;

import io.github.resilience4j.retry.annotation.Retry;
import java.util.List;
import java.util.Optional;
import ru.dankoy.hw19.core.domain.Author;
import ru.dankoy.hw19.core.domain.Work;
import ru.dankoy.hw19.core.domain.Genre;

public interface WorkService {

  List<Work> findAllByGenreName(Genre genre);

  List<Work> findAllByAuthorId(Author author);

  List<Work> findAll();

  Optional<Work> getById(String id);

  Work insert(Work work);

  @Retry(name = "bookService")
  Work update(Work work);

  void deleteById(String id);

  long count();

  void updateMultiple(List<Work> works);
}
