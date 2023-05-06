package ru.dankoy.hw19.core.service.work;

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

  Work insertOrUpdate(Work work);

  void deleteById(String id);

  long count();

  void updateMultiple(List<Work> works);
}
