package ru.dankoy.hw16.core.repository.book;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.dankoy.hw16.core.domain.Book;


@RepositoryRestResource(path = "book")
public interface BookRepository extends MongoRepository<Book, String>, BookRepositoryCustom {

  @RestResource(path = "books-by-genres", rel = "books-by-genres")
  @Query("{'genres.name' : :#{#genreName}}")
  List<Book> findBookByGenres(@Param("genreName") String genreName);


  @RestResource(path = "books-by-author", rel = "books-by-author")
  List<Book> findBookByAuthorsId(String authorId);

}
