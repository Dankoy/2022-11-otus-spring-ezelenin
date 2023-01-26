package ru.dankoy.hw8.core.repository.commentary;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.dankoy.hw8.core.domain.Commentary;


public interface CommentaryRepository extends MongoRepository<Commentary, String> {

}
