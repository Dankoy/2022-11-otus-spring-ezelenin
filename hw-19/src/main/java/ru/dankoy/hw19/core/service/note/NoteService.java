package ru.dankoy.hw19.core.service.note;

import java.util.Optional;
import java.util.Set;
import ru.dankoy.hw19.core.domain.Note;

public interface NoteService {

  Note create(Note note);

  Optional<Note> findById(String noteId);

  void deleteById(String noteId);

  Note update(Note note);

  Set<Note> findAll();

  Optional<Note> findByEditionId(String editionId);
}
