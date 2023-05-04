package ru.dankoy.hw19.core.service.book;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.dankoy.hw19.core.domain.Author;
import ru.dankoy.hw19.core.domain.Book;
import ru.dankoy.hw19.core.domain.Genre;
import ru.dankoy.hw19.core.repository.book.BookRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookServiceMongo implements BookService {

  private final Random random = new Random();
  private final BookRepository bookRepository;


  @CircuitBreaker(name = "bookService", fallbackMethod = "fallbackListBooksByGenre")
  @Override
  public List<Book> findAllByGenreName(Genre genre) {

    throwException();
    return bookRepository.findBookByGenres(genre.getName());

  }

  @CircuitBreaker(name = "bookService", fallbackMethod = "fallbackListBooksByAuthor")
  @Override
  public List<Book> findAllByAuthorId(Author author) {

    throwException();
    return bookRepository.findBookByAuthorsId(author.getId());

  }

  @CircuitBreaker(name = "bookService", fallbackMethod = "fallbackListBooks")
  @Override
  public List<Book> findAll() {
    throwException();
    return bookRepository.findAll();
  }

  @CircuitBreaker(name = "bookService", fallbackMethod = "fallbackOptionalBook")
  @Override
  public Optional<Book> getById(String id) {
    throwException();
    return bookRepository.findById(id);
  }

  @Retry(name = "bookService")
  @Override
  public Book insertOrUpdate(Book book) {

    return bookRepository.saveAndCheckAuthors(book);
  }

  @Retry(name = "bookService")
  @Override
  public void deleteById(String id) {
    bookRepository.deleteByBookId(id);
  }

  @CircuitBreaker(name = "bookService", fallbackMethod = "fallbackCount")
  @Override
  public long count() {
    return bookRepository.count();
  }


  @Override
  public void updateMultiple(List<Book> books) {
    bookRepository.saveAll(books);
  }



  // ---------------- resilience methods --------------------

  public List<Book> fallbackListBooksByGenre(Genre genre, RuntimeException ex) {
    log.info("fallbackFindBooksByGenre - {}", ex.getMessage());

    return new ArrayList<>();

  }

  public List<Book> fallbackListBooksByAuthor(Author author, RuntimeException ex) {
    log.info("fallbackListBooksByAuthor - {}", ex.getMessage());

    return new ArrayList<>();

  }

  public List<Book> fallbackListBooks(RuntimeException ex) {
    log.info("fallbackListBooks - {}", ex.getMessage());

    return new ArrayList<>();

  }

  public Optional<Book> fallbackOptionalBook(String id, RuntimeException ex) {
    log.info("fallbackOptionalBook - {}", ex.getMessage());

    return Optional.of(new Book(id, null, new HashSet<>(), new HashSet<>()));

  }

  public long fallbackCount(RuntimeException ex) {
    log.info("fallbackCount - {}", ex.getMessage());

    return 999L;
  }


  private void throwException() {

    var r = random.nextInt(5);

    if (r < 2) {

      log.info("throwing runtime exception");
      throw new RuntimeException();

    }
  }

}
