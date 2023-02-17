package ru.dankoy.hw10.core.service.commentary;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dankoy.hw10.core.domain.Commentary;
import ru.dankoy.hw10.core.exceptions.Entity;
import ru.dankoy.hw10.core.exceptions.EntityNotFoundException;
import ru.dankoy.hw10.core.repository.commentary.CommentaryRepository;


@RequiredArgsConstructor
@Service
public class CommentaryServiceMongo implements CommentaryService {

  private final CommentaryRepository commentaryRepository;


  @Override
  public List<Commentary> getAllByBookId(String id) {
    return commentaryRepository.findAllByBookId(id);
  }


  @Override
  public void deleteAllByBookId(String bookId) {

    commentaryRepository.deleteCommentariesByBookId(bookId);

  }

  @Override
  public Optional<Commentary> getById(String id) {
    return commentaryRepository.findById(id);
  }


  @Override
  public Commentary insertOrUpdate(Commentary commentary) {

    return commentaryRepository.save(commentary);
  }

  @Override
  public void deleteById(String id) {
    var optional = commentaryRepository.findById(id);
    var commentary = optional.orElseThrow(() -> new EntityNotFoundException(id, Entity.COMMENTARY));

    commentaryRepository.delete(commentary);

  }

}
