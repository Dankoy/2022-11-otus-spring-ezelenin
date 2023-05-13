package ru.dankoy.hw19.core.repository.shelf;

import java.util.Set;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.dankoy.hw19.core.domain.Shelf;

@RepositoryRestResource(path = "shelf")
public interface ShelfRepository extends MongoRepository<Shelf, String> {

  @RestResource(path = "find-by-name", rel = "find-by-name")
  Shelf findByName(String name);

  @RestResource(path = "find-by-user-id", rel = "find-by-user-id")
  Set<Shelf> findAllByUserId(String userId);

}
