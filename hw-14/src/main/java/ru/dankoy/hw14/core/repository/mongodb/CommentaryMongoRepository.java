package ru.dankoy.hw14.core.repository.mongodb;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import ru.dankoy.hw14.core.domain.mongodb.Commentary;

public interface CommentaryMongoRepository extends MongoRepository<Commentary, String> {

  List<Commentary> findByBookId(String bookId);

}
