package ru.dankoy.hw19.core.service.genre;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dankoy.hw19.core.domain.Work;
import ru.dankoy.hw19.core.domain.Genre;
import ru.dankoy.hw19.core.service.work.WorkService;

@Service
@RequiredArgsConstructor
public class GenreServiceMongo implements GenreService {

  private final WorkService workService;

  @Override
  public void update(Genre oldGenre, Genre newGenre) {

    List<Work> works = workService.findAllByGenreName(oldGenre);

    works.forEach(b -> {
      b.getGenres().remove(oldGenre);
      b.getGenres().add(newGenre);
    });

    workService.updateMultiple(works);

  }

  @Override
  public void delete(Genre genre) {

    List<Work> works = workService.findAllByGenreName(genre);

    works.forEach(b -> b.getGenres().remove(genre));

    workService.updateMultiple(works);

  }


  @Override
  public Set<Genre> getAllGenres() {
    var books = workService.findAll();

    return books.stream().flatMap(b -> b.getGenres().stream())
        .collect(Collectors.toSet());

  }

}
