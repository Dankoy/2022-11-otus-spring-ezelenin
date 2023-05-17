package ru.dankoy.hw19.core.repository.work;

import java.util.List;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.dankoy.hw19.core.domain.Work;
import ru.dankoy.hw19.core.domain.Genre;

public interface WorkRepositoryCustom {


  @RestResource(path = "genres-by-book", rel = "genres-by-book")
  List<Genre> getAllGenresByBookId(String bookId);

  @RestResource(path = "save-book", rel = "save-book")
  Work saveAndCheckAuthors(Work work);

  @RestResource(path = "delete-book", rel = "delete-book")
  void deleteByWorkId(String bookId);
}
