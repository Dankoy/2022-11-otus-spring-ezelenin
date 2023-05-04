package ru.dankoy.hw11.core.repository.author;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.dankoy.hw11.core.domain.Author;

public interface AuthorRepository extends ReactiveMongoRepository<Author, String> {


  @Query(value = "{ '_id' : {'$in' : :#{#ids} } }")
  Flux<Author> findAllByAuthorId(@Param("ids") Iterable<String> ids);

  Mono<Author> findByName(String name);


  @Query(value = "{ 'name' : {'$in' : :#{#names} } }")
  Flux<Author> findAllByNames(@Param("names") Iterable<String> names);

}
