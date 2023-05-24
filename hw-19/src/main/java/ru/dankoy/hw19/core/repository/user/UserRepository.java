package ru.dankoy.hw19.core.repository.user;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.dankoy.hw19.core.domain.User;


@RepositoryRestResource(path = "user")
public interface UserRepository extends MongoRepository<User, String> {

  @RestResource(path = "find-by-name", rel = "find-by-name")
  Optional<User> findByUsername(String username);

}
