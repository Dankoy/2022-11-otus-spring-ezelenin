package ru.dankoy.hw19.core.service.note;

import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dankoy.hw19.core.domain.Note;
import ru.dankoy.hw19.core.repository.note.NoteRepository;


@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

  private final NoteRepository noteRepository;

  @Override
  public Note create(Note note) {
    return noteRepository.save(note);
  }

  @Override
  public void deleteById(String noteId) {

    var optionalNote = noteRepository.findById(noteId);
    optionalNote.ifPresent(noteRepository::delete);

  }

  @Override
  public Note update(Note note) {
    return noteRepository.save(note);
  }

  @Override
  public Set<Note> findByUserId(String userId) {
    return noteRepository.findAllByUserId(userId);
  }

  @Override
  public Note findByUserIdAndEditionId(String userId, String editionId) {
    return noteRepository.findByUserIdAndEditionId(userId, editionId);
  }
}
