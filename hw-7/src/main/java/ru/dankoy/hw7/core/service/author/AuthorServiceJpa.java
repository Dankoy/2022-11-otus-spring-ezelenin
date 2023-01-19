package ru.dankoy.hw7.core.service.author;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dankoy.hw7.core.repository.author.AuthorRepository;
import ru.dankoy.hw7.core.domain.Author;
import ru.dankoy.hw7.core.exceptions.EntityNotFoundException;

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

  @Transactional
  @Override
  public Author insertOrUpdate(Author author) {
    return authorRepository.save(author);
  }

  @Transactional
  @Override
  public void deleteById(long id) {
    var optional = authorRepository.getById(id);
    var author = optional.orElseThrow(() -> new EntityNotFoundException(
        String.format("Entity %s has not been found with id - %d", Author.class.getName(), id)));
    authorRepository.delete(author);
  }

  @Override
  public long count() {
    return authorRepository.count();
  }
}
