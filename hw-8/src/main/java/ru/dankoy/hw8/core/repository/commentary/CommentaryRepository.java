package ru.dankoy.hw8.core.repository.commentary;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import ru.dankoy.hw8.core.domain.Commentary;

// Создание бина работает только с MongoRepository. С CrudRepository не работает

public interface CommentaryRepository extends MongoRepository<Commentary, String> {

}
