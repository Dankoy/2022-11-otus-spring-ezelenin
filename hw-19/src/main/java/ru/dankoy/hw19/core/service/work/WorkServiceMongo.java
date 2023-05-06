package ru.dankoy.hw19.core.service.work;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.dankoy.hw19.core.domain.Author;
import ru.dankoy.hw19.core.domain.Genre;
import ru.dankoy.hw19.core.domain.Work;
import ru.dankoy.hw19.core.repository.work.BookRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class WorkServiceMongo implements WorkService {

  private final BookRepository bookRepository;


  @CircuitBreaker(name = "bookService", fallbackMethod = "fallbackListBooksByGenre")
  @Override
  public List<Work> findAllByGenreName(Genre genre) {

    return bookRepository.findBookByGenres(genre.getName());

  }

  @CircuitBreaker(name = "bookService", fallbackMethod = "fallbackListBooksByAuthor")
  @Override
  public List<Work> findAllByAuthorId(Author author) {

    return bookRepository.findBookByAuthorsId(author.getId());

  }

  @CircuitBreaker(name = "bookService", fallbackMethod = "fallbackListBooks")
  @Override
  public List<Work> findAll() {
    return bookRepository.findAll();
  }

  @CircuitBreaker(name = "bookService", fallbackMethod = "fallbackOptionalBook")
  @Override
  public Optional<Work> getById(String id) {
    return bookRepository.findById(id);
  }

  @Retry(name = "bookService")
  @Override
  public Work insertOrUpdate(Work work) {

    return bookRepository.saveAndCheckAuthors(work);
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
  public void updateMultiple(List<Work> works) {
    bookRepository.saveAll(works);
  }


}
