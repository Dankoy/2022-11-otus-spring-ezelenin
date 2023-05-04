package ru.dankoy.hw11.core.repository.commentary;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.dankoy.hw11.core.domain.Commentary;


public interface CommentaryRepository extends ReactiveMongoRepository<Commentary, String> {

  Mono<Void> deleteCommentariesByBookId(Mono<String> bookId);

  Flux<Commentary> findAllByBookId(Mono<String> bookId);

}
