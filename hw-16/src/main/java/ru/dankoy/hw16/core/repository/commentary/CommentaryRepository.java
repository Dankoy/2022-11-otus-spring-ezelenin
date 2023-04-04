package ru.dankoy.hw16.core.repository.commentary;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import ru.dankoy.hw16.core.domain.Commentary;


public interface CommentaryRepository extends MongoRepository<Commentary, String> {

  void deleteCommentariesByBookId(String bookId);

  List<Commentary> findAllByBookId(String bookId);

}
