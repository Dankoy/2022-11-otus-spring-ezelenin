package ru.dankoy.hw8.core.repository.book;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.dankoy.hw8.core.domain.Book;


public interface BookRepository extends MongoRepository<Book, String> {

}
