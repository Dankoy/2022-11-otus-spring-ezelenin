package ru.dankoy.hw16.core.repository.book;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.dankoy.hw16.core.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long>, BookRepositoryCustom {

  Optional<Book> getById(long id);

}
