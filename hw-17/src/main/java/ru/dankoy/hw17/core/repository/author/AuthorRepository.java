package ru.dankoy.hw17.core.repository.author;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.dankoy.hw17.core.domain.Author;


@RepositoryRestResource(path = "author")
public interface AuthorRepository extends MongoRepository<Author, String> {


}
