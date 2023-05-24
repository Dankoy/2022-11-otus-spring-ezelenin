package ru.dankoy.hw19.core.repository.work;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.dankoy.hw19.core.domain.Work;


@RepositoryRestResource(path = "work")
public interface WorkRepository extends MongoRepository<Work, String>, WorkRepositoryCustom {


  @RestResource(path = "works-by-genres", rel = "works-by-genres")
  @Query("{'genres.name' : :#{#genreName}}")
  List<Work> findBookByGenres(@Param("genreName") String genreName);


  @RestResource(path = "works-by-author", rel = "works-by-author")
  List<Work> findBookByAuthorsId(String authorId);

}
