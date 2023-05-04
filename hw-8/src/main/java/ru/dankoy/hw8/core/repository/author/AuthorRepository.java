package ru.dankoy.hw8.core.repository.author;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.dankoy.hw8.core.domain.Author;

public interface AuthorRepository extends MongoRepository<Author, String> {


}
