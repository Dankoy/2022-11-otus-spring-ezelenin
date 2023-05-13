package ru.dankoy.hw19.core.repository.note;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.dankoy.hw19.core.domain.Note;

@RepositoryRestResource(path = "note")
public interface NoteRepository extends MongoRepository<Note, String> {

}
