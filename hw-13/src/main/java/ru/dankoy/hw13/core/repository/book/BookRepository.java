package ru.dankoy.hw13.core.repository.book;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.dankoy.hw13.core.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long>, BookRepositoryCustom {

  Optional<Book> getById(long id);

}
