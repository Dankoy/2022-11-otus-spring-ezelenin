package ru.dankoy.hw19.core.service.author;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dankoy.hw19.core.domain.Author;
import ru.dankoy.hw19.core.domain.Work;
import ru.dankoy.hw19.core.exceptions.Entity;
import ru.dankoy.hw19.core.exceptions.EntityNotFoundException;
import ru.dankoy.hw19.core.repository.author.AuthorRepository;
import ru.dankoy.hw19.core.service.work.WorkService;

@Service
@RequiredArgsConstructor
public class AuthorServiceMongo implements AuthorService {

  private final AuthorRepository authorRepository;

  private final WorkService workService;

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
    var author = optional.orElseThrow(() -> new EntityNotFoundException(id, Entity.AUTHOR));

    List<Work> works = workService.findAllByAuthorId(author);

    works.forEach(b -> b.getAuthors().remove(author));

    workService.updateMultiple(works);

    authorRepository.delete(author);
  }

  @Override
  public long count() {
    return authorRepository.count();
  }
}
