package ru.dankoy.hw8.core.repository.commentary;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import ru.dankoy.hw8.core.domain.Commentary;


public interface CommentaryRepository extends MongoRepository<Commentary, String> {

  void deleteCommentariesByBookId(String bookId);

  List<Commentary> findAllByBookId(String bookId);

}
