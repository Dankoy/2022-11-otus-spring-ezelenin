package ru.dankoy.hw11.core.repository.book;

import java.util.List;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Flux;
import ru.dankoy.hw11.core.domain.Book;


public interface BookRepository extends ReactiveMongoRepository<Book, String>,
    BookRepositoryCustom {

  @Query("{'genres.name' : :#{#genreName}}")
  Flux<Book> findBookByGenres(@Param("genreName") String genreName);


  Flux<Book> findBookByAuthorsId(String authorId);

}
