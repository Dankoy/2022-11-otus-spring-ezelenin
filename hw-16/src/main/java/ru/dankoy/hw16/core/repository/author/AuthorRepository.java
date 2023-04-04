package ru.dankoy.hw16.core.repository.author;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.dankoy.hw16.core.domain.Author;

public interface AuthorRepository extends MongoRepository<Author, String> {


}
