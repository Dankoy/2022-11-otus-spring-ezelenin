package ru.dankoy.hw19.core.repository.note;

import java.util.Set;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.dankoy.hw19.core.domain.Note;

@RepositoryRestResource(path = "note")
public interface NoteRepository extends MongoRepository<Note, String> {

  Set<Note> findAllByUserId(String userId);

  Note findByUserIdAndEditionId(String userId, String editionId);

}
