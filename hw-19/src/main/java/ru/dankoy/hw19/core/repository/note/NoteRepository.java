package ru.dankoy.hw19.core.repository.note;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.dankoy.hw19.core.domain.Note;

@RepositoryRestResource(path = "note")
public interface NoteRepository extends MongoRepository<Note, String> {

  @RestResource(path = "find-by-id", rel = "find-by-id")
  Optional<Note> findByIdAndUserId(String id, String userId);

  @RestResource(path = "find-by-edition-id", rel = "find-by-edition-id")
  Optional<Note> findAllByUserIdAndEditionId(String userId, String editionId);

}
