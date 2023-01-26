package ru.dankoy.hw8.core.repository.genre;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import ru.dankoy.hw8.core.domain.Genre;

// Создание бина работает только с MongoRepository. С CrudRepository не работает

public interface GenreRepository extends MongoRepository<Genre, String> {

}
