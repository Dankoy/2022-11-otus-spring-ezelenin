package ru.dankoy.hw14.core.repository.sql;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dankoy.hw14.core.domain.sql.Book;

public interface BookSqlRepository extends JpaRepository<Book, Long> {

}
