package ru.dankoy.hw17.core.repository.commentary;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.dankoy.hw17.core.domain.Commentary;


@RepositoryRestResource(path = "commentary")
public interface CommentaryRepository extends MongoRepository<Commentary, String> {

  @RestResource(path = "delete-by-book-id", rel = "delete-by-book-id")
  void deleteCommentariesByBookId(String bookId);

  @RestResource(path = "all-by-book-id", rel = "all-by-book-id")
  List<Commentary> findAllByBookId(String bookId);

}
