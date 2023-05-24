package ru.dankoy.hw19.core.repository.edition;

import java.util.Set;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.dankoy.hw19.core.domain.Edition;

@RepositoryRestResource(path = "edition")
public interface EditionRepository extends MongoRepository<Edition, String>,
    EditionRepositoryCustom {

  Set<Edition> findAllByWorkId(String workId);

}
