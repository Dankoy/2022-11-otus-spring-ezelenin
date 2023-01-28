package ru.dankoy.hw8.core.repository.book;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.dankoy.hw8.core.domain.Book;


public interface BookRepository extends MongoRepository<Book, String>, BookRepositoryCustom {

  @Query("{'genres.name' : :#{#genreName}}")
  List<Book> findBookByGenres(@Param("genreName") String genreName);

}
