package ru.dankoy.hw19.core.service.note;

import java.util.Set;
import ru.dankoy.hw19.core.domain.Note;

public interface NoteService {

  Note create(Note note);

  void deleteById(String noteId);

  Note update(Note note);

  Set<Note> findByUserId(String userId);

  Note findByUserIdAndEditionId(String userId, String editionId);

}
