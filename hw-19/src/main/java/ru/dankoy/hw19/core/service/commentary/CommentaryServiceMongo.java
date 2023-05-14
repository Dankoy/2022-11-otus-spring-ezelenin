package ru.dankoy.hw19.core.service.commentary;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dankoy.hw19.core.aspects.CommentaryCurrentUser;
import ru.dankoy.hw19.core.domain.Commentary;
import ru.dankoy.hw19.core.exceptions.Entity;
import ru.dankoy.hw19.core.exceptions.EntityNotFoundException;
import ru.dankoy.hw19.core.repository.commentary.CommentaryRepository;


@RequiredArgsConstructor
@Service
public class CommentaryServiceMongo implements CommentaryService {

  private final CommentaryRepository commentaryRepository;


  @Override
  public List<Commentary> getAllByBookId(String id) {
    return commentaryRepository.findAllByWorkId(id);
  }


  @Override
  public void deleteAllByBookId(String bookId) {

    commentaryRepository.deleteCommentariesByWorkId(bookId);

  }

  @Override
  public Optional<Commentary> getById(String id) {
    return commentaryRepository.findById(id);
  }


  @Override
  @CommentaryCurrentUser
  public Commentary insertOrUpdate(Commentary commentary) {

    // todo: вынести в отдельный метод сохранения?
    if (Objects.nonNull(commentary.getId())) {
      var optionalWork = commentaryRepository.findById(commentary.getId());
      optionalWork.ifPresent(w -> commentary.setDateCreated(w.getDateCreated()));
    }

    return commentaryRepository.save(commentary);
  }

  @Override
  public void deleteById(String id) {
    var optional = commentaryRepository.findById(id);
    var commentary = optional.orElseThrow(() -> new EntityNotFoundException(id, Entity.COMMENTARY));

    commentaryRepository.delete(commentary);

  }

}
