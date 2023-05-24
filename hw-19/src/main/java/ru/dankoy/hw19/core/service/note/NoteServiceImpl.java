package ru.dankoy.hw19.core.service.note;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.dankoy.hw19.core.aspects.AddCreatedMetadata;
import ru.dankoy.hw19.core.domain.Note;
import ru.dankoy.hw19.core.domain.User;
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
  public Optional<Note> findById(String noteId) {

    var user = (User) SecurityContextHolder.getContext()
        .getAuthentication()
        .getPrincipal();

    return noteRepository.findByCreatedByUserIdAndId(user.getId(), noteId);

  }

  @Override
  public void deleteById(String noteId) {

    var user = (User) SecurityContextHolder.getContext()
        .getAuthentication()
        .getPrincipal();

    var optionalNote = noteRepository.findByCreatedByUserIdAndId(user.getId(), noteId);
    optionalNote.ifPresent(noteRepository::delete);

  }


  @Override
  @AddCreatedMetadata
  public Note update(Note note) {
    return noteRepository.save(note);
  }

  @PostFilter(value = "filterObject.createdByUser.getId() == authentication.principal.id")
  @Override
  public Set<Note> findAll() {
    return new HashSet<>(noteRepository.findAll());
  }

  @Override
  public Optional<Note> findByEditionId(String editionId) {

    var user = (User) SecurityContextHolder.getContext()
        .getAuthentication()
        .getPrincipal();

    return noteRepository.findAllByCreatedByUserIdAndEditionId(user.getId(), editionId);
  }
}
