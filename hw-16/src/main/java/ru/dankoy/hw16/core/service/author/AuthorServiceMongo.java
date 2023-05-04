package ru.dankoy.hw16.core.service.author;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dankoy.hw16.core.domain.Author;
import ru.dankoy.hw16.core.domain.Book;
import ru.dankoy.hw16.core.exceptions.Entity;
import ru.dankoy.hw16.core.exceptions.EntityNotFoundException;
import ru.dankoy.hw16.core.repository.author.AuthorRepository;
import ru.dankoy.hw16.core.service.book.BookService;

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

  @Override
  public Author insertOrUpdate(Author author) {
    return authorRepository.save(author);
  }

  @Override
  public void deleteById(String id) {
    var optional = authorRepository.findById(id);
    var author = optional.orElseThrow(() -> new EntityNotFoundException(id, Entity.AUTHOR));

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
