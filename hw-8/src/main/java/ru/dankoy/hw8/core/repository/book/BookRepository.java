package ru.dankoy.hw8.core.repository.book;

import com.mongodb.client.MongoDatabase;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import ru.dankoy.hw8.core.domain.Book;


// Создание бина работает только с MongoRepository. С CrudRepository не работает
public interface BookRepository extends MongoRepository<Book, String> {

}
