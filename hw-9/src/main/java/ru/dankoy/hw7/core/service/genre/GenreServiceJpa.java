package ru.dankoy.hw7.core.service.genre;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dankoy.hw7.core.domain.Genre;
import ru.dankoy.hw7.core.repository.genre.GenreRepository;

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

  @Override
  public Genre insertOrUpdate(Genre genre) {
    return genreRepository.save(genre);
  }

  @Override
  public void deleteById(long id) {
    var optional = genreRepository.getById(id);
    optional.ifPresent(genreRepository::delete);
  }

  @Override
  public long count() {
    return genreRepository.count();
  }
}
