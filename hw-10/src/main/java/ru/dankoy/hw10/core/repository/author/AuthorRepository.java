package ru.dankoy.hw10.core.repository.author;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.dankoy.hw10.core.domain.Author;

public interface AuthorRepository extends MongoRepository<Author, String> {


}
