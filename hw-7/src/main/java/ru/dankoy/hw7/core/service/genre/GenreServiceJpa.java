package ru.dankoy.hw7.core.service.genre;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dankoy.hw7.core.repository.genre.GenreRepository;
import ru.dankoy.hw7.core.domain.Genre;
import ru.dankoy.hw7.core.exceptions.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class GenreServiceJpa implements GenreService {

  private final GenreRepository genreRepository;

  @Override
  public List<Genre> getAll() {
    return genreRepository.findAll();
  }

  @Override
  public Optional<Genre> getById(long id) {
    return genreRepository.getById(id);
  }

  @Transactional
  @Override
  public Genre insertOrUpdate(Genre genre) {
    return genreRepository.save(genre);
  }

  @Transactional
  @Override
  public void deleteById(long id) {
    var optional = genreRepository.getById(id);
    var genre = optional.orElseThrow(() -> new EntityNotFoundException(
        String.format("Entity %s has not been found with id - %d", Genre.class.getName(), id)));
    genreRepository.delete(genre);
  }

  @Override
  public long count() {
    return genreRepository.count();
  }
}
