package ru.dankoy.hw16.core.repository.author;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.dankoy.hw16.core.domain.Author;


@RepositoryRestResource(path = "author")
public interface AuthorRepository extends MongoRepository<Author, String> {


}
