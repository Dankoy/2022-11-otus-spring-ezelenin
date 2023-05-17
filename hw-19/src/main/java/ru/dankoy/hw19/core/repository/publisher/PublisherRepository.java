package ru.dankoy.hw19.core.repository.publisher;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.dankoy.hw19.core.domain.Publisher;

@RepositoryRestResource(path = "publisher")
public interface PublisherRepository extends MongoRepository<Publisher, String>,
    PublisherRepositoryCustom {

}
