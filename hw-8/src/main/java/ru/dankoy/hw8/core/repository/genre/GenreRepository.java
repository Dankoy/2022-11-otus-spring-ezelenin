package ru.dankoy.hw8.core.repository.genre;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.dankoy.hw8.core.domain.Genre;


public interface GenreRepository extends MongoRepository<Genre, String> {

}
