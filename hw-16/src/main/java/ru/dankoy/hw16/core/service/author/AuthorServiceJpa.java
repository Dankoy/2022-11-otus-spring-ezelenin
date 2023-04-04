package ru.dankoy.hw16.core.service.author;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dankoy.hw16.core.domain.Author;
import ru.dankoy.hw16.core.repository.author.AuthorRepository;

@Service
@RequiredArgsConstructor
public class AuthorServiceJpa implements AuthorService {

  private final AuthorRepository authorRepository;

  @Override
  public List<Author> getAll() {
    return authorRepository.findAll();
  }

  @Override
  public Optional<Author> getById(long id) {
    return authorRepository.getById(id);
  }

  @Override
  public Author insertOrUpdate(Author author) {
    return authorRepository.save(author);
  }

  @Override
  public void deleteById(long id) {
    var optional = authorRepository.getById(id);
    optional.ifPresent(authorRepository::delete);
  }

  @Override
  public long count() {
    return authorRepository.count();
  }
}
