package ru.dankoy.hw8.core.service.author;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dankoy.hw8.core.domain.Author;
import ru.dankoy.hw8.core.exceptions.EntityNotFoundException;
import ru.dankoy.hw8.core.repository.author.AuthorRepository;

@Service
@RequiredArgsConstructor
public class AuthorServiceMongo implements AuthorService {

  private final AuthorRepository authorRepository;
  @Override
  public List<Author> getAll() {
    return authorRepository.findAll();
  }

  @Override
  public Optional<Author> getById(String id) {
    return authorRepository.findById(id);
  }

  @Override
  public Author insertOrUpdate(Author author) {
    return authorRepository.save(author);
  }

  @Override
  public void deleteById(String id) {
    var optional = authorRepository.findById(id);
    var author = optional.orElseThrow(() -> new EntityNotFoundException(
        String.format("Entity %s has not been found with id - %s", Author.class.getName(), id)));
    authorRepository.delete(author);
  }

  @Override
  public long count() {
    return authorRepository.count();
  }
}
