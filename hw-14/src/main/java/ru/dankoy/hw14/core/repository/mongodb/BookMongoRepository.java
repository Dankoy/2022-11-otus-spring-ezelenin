package ru.dankoy.hw14.core.repository.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.dankoy.hw14.core.domain.mongodb.Book;

public interface BookMongoRepository extends MongoRepository<Book, String> {

}
