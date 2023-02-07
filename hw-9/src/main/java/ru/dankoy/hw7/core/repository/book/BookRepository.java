package ru.dankoy.hw7.core.repository.book;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.dankoy.hw7.core.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long>, BookRepositoryCustom {

  @EntityGraph(value = "authors-entity-graph")
  List<Book> findAll();

  Optional<Book> getById(long id);

}
