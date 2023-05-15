package ru.dankoy.hw19.core.service.work;

import io.github.resilience4j.retry.annotation.Retry;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.dankoy.hw19.core.domain.Author;
import ru.dankoy.hw19.core.domain.Genre;
import ru.dankoy.hw19.core.domain.Work;
import ru.dankoy.hw19.core.repository.work.WorkRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class WorkServiceMongo implements WorkService {

  private final WorkRepository workRepository;


  //  @CircuitBreaker(name = "bookService", fallbackMethod = "fallbackListBooksByGenre")
  @Override
  public List<Work> findAllByGenreName(Genre genre) {

    return workRepository.findBookByGenres(genre.getName());

  }

  //  @CircuitBreaker(name = "bookService", fallbackMethod = "fallbackListBooksByAuthor")
  @Override
  public List<Work> findAllByAuthorId(Author author) {

    return workRepository.findBookByAuthorsId(author.getId());

  }

  //  @CircuitBreaker(name = "bookService", fallbackMethod = "fallbackListBooks")
  @Override
  public List<Work> findAll() {
    return workRepository.findAll();
  }

  //  @CircuitBreaker(name = "bookService", fallbackMethod = "fallbackOptionalBook")
  @Override
  public Optional<Work> getById(String id) {
    return workRepository.findById(id);
  }

  @Retry(name = "bookService")
  @Override
  public Work insert(Work work) {

    // todo: вынести в отдельный метод сохранения?
    if (Objects.nonNull(work.getId())) {
      var optionalWork = workRepository.findById(work.getId());
      optionalWork.ifPresent(w -> {
        work.setDateCreated(w.getDateCreated());
        work.setCreatedByUser(w.getCreatedByUser());
      });
    }

    return workRepository.saveAndCheckAuthors(work);
  }

  @Retry(name = "bookService")
  @Override
  public Work update(Work work) {

    var optionalWork = workRepository.findById(work.getId());
    optionalWork.ifPresent(w -> {
      work.setDateCreated(w.getDateCreated());
      work.setCreatedByUser(w.getCreatedByUser());
    });

    return workRepository.saveAndCheckAuthors(work);
  }

  @Retry(name = "bookService")
  @Override
  public void deleteById(String id) {
    workRepository.deleteByBookId(id);
  }

  //  @CircuitBreaker(name = "bookService", fallbackMethod = "fallbackCount")
  @Override
  public long count() {
    return workRepository.count();
  }


  @Override
  public void updateMultiple(List<Work> works) {
    workRepository.saveAll(works);
  }


}
