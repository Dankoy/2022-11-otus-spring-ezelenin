package ru.dankoy.hw8.core.service.genre;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dankoy.hw8.core.domain.Genre;
import ru.dankoy.hw8.core.exceptions.EntityNotFoundException;
import ru.dankoy.hw8.core.repository.book.BookRepository;
import ru.dankoy.hw8.core.repository.genre.GenreRepository;
import ru.dankoy.hw8.core.service.book.BookService;

@Service
@RequiredArgsConstructor
public class GenreServiceMongo implements GenreService {

  private final GenreRepository genreRepository;

  @Override
  public List<Genre> getAll() {
    return (List<Genre>) genreRepository.findAll();
  }

  @Override
  public Optional<Genre> getById(String id) {
    return genreRepository.findById(id);
  }

  @Override
  public Genre insertOrUpdate(Genre genre) {
    return genreRepository.save(genre);
  }

  @Override
  public void deleteById(String id) {
    var optional = genreRepository.findById(id);
    var genre = optional.orElseThrow(() -> new EntityNotFoundException(
        String.format("Entity %s has not been found with id - %d", Genre.class.getName(), id)));
    genreRepository.delete(genre);
  }

  @Override
  public long count() {
    return genreRepository.count();
  }
}
