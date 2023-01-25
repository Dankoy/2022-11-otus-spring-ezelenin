package ru.dankoy.hw8.core.repository.book;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import ru.dankoy.hw8.core.domain.Book;

public interface BookRepository extends CrudRepository<Book, Long> {

}
