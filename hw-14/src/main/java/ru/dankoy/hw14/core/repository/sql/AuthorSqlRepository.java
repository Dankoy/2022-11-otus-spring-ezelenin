package ru.dankoy.hw14.core.repository.sql;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dankoy.hw14.core.domain.sql.Author;

public interface AuthorSqlRepository extends JpaRepository<Author, Long> {

}
