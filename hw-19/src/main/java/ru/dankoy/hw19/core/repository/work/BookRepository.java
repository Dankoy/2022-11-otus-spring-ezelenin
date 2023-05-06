package ru.dankoy.hw19.core.repository.work;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.dankoy.hw19.core.domain.Work;


@RepositoryRestResource(path = "book")
public interface BookRepository extends MongoRepository<Work, String>, BookRepositoryCustom {

  @RestResource(path = "books-by-genres", rel = "books-by-genres")
  @Query("{'genres.name' : :#{#genreName}}")
  List<Work> findBookByGenres(@Param("genreName") String genreName);


  @RestResource(path = "books-by-author", rel = "books-by-author")
  List<Work> findBookByAuthorsId(String authorId);

}
