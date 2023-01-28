package ru.dankoy.hw8.core.service.author;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dankoy.hw8.core.domain.Author;
import ru.dankoy.hw8.core.domain.Book;
import ru.dankoy.hw8.core.exceptions.EntityNotFoundException;
import ru.dankoy.hw8.core.repository.author.AuthorRepository;
import ru.dankoy.hw8.core.service.book.BookService;

@Service
@RequiredArgsConstructor
public class AuthorServiceMongo implements AuthorService {

  private final AuthorRepository authorRepository;

  private final BookService bookService;

  @Override
  public List<Author> getAll() {
    return authorRepository.findAll();
  }

  @Override
  public Optional<Author> getById(String id) {
    return authorRepository.findById(id);
  }

  @Transactional
  @Override
  public Author insertOrUpdate(Author author) {
    return authorRepository.save(author);
  }

  @Transactional
  @Override
  public void deleteById(String id) {
    var optional = authorRepository.findById(id);
    var author = optional.orElseThrow(() -> new EntityNotFoundException(
        String.format("Entity %s has not been found with id - %s", Author.class.getName(), id)));

    List<Book> books = bookService.findAllByAuthorId(author);

    books.forEach(b -> b.getAuthors().remove(author));

    bookService.updateMultiple(books);

    authorRepository.delete(author);
  }

  @Override
  public long count() {
    return authorRepository.count();
  }
}
