package ru.dankoy.hw19.core.service.commentary;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.bulkhead.annotation.Bulkhead.Type;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeoutException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Service;
import ru.dankoy.hw19.core.domain.Commentary;
import ru.dankoy.hw19.core.exceptions.Entity;
import ru.dankoy.hw19.core.exceptions.EntityNotFoundException;
import ru.dankoy.hw19.core.exceptions.Hw5RootException;
import ru.dankoy.hw19.core.repository.commentary.CommentaryRepository;

@Slf4j
@RequiredArgsConstructor
@Service
@ConditionalOnClass(name = "io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker")
public class CommentaryResilienceImitationService implements ResilienceCommentaryService {

  private final Random random = new Random();
  private final CommentaryRepository commentaryRepository;

  @CircuitBreaker(name = "commentaryService", fallbackMethod = "fallback")
  @TimeLimiter(name = "commentaryService", fallbackMethod = "timeLimiterFallback")
  @Bulkhead(name = "commentaryService", type = Type.THREADPOOL)
  // нужен что бы работал TimeLimiter без асинка
  @Override
  public CompletableFuture<List<Commentary>> getAllByBookId(String id) {
    var r = random.nextInt(5);
    // имитация fallback для circuit breaker
    if (r < 3) {
      throw new RuntimeException("imitate");
    } else {
      sleep();
    }
    return CompletableFuture.completedFuture(commentaryRepository.findAllByWorkId(id));
  }

  @Override
  public void deleteAllByBookId(String bookId) {

    commentaryRepository.deleteCommentariesByWorkId(bookId);

  }

  @Override
  public CompletableFuture<Optional<Commentary>> getById(String id) {
    return CompletableFuture.completedFuture(commentaryRepository.findById(id));
  }


  @Override
  public CompletableFuture<Commentary> insertOrUpdate(Commentary commentary) {

    return CompletableFuture.completedFuture(commentaryRepository.save(commentary));
  }


  @Retry(name = "commentaryService")
  @Override
  public void deleteById(String id) {

    var optional = commentaryRepository.findById(id);
    var commentary = optional.orElseThrow(() -> new EntityNotFoundException(id, Entity.COMMENTARY));

    commentaryRepository.delete(commentary);

  }

  // ---------------- resilience methods --------------------

  // TimeLimiter требует CompletableFuture или CompletableStage как возвращаемый параметр метода
  public CompletableFuture<List<Commentary>> fallback(String id, RuntimeException ex) {

    log.info("fallbackMethod - {}", id);
    return CompletableFuture.completedFuture(new ArrayList<>());

  }

  public CompletableFuture<List<Commentary>> timeLimiterFallback(String id, TimeoutException ex) {
    log.info("time limiter fallback - {}", id);
    // Можно сделать запрос в другой сервис или залезть в кэш
    return CompletableFuture.completedFuture(new ArrayList<>());
  }

  private void sleep() {

    var r = random.nextInt(5);
    var sleepTime = random.nextLong(5000); // если меньше 3000, то timelimiter не сработает

    if (r < 2) {
      try {
        log.info("Sleeping - {} ms", sleepTime);
        Thread.sleep(sleepTime);
      } catch (InterruptedException e) {
        throw new Hw5RootException("Interrupted", e);
      }
    }
  }
}
