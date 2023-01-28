package ru.dankoy.hw8.core.service.commentary;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dankoy.hw8.core.domain.Commentary;
import ru.dankoy.hw8.core.repository.commentary.CommentaryRepository;
import ru.dankoy.hw8.core.service.utils.OptionalChecker;


@RequiredArgsConstructor
@Service
public class CommentaryServiceMongo implements CommentaryService {

  private final CommentaryRepository commentaryRepository;

  private final OptionalChecker optionalChecker;


  @Override
  public List<Commentary> getAllByBookId(String id) {
    return commentaryRepository.findAllByBookId(id);
  }


  @Transactional
  @Override
  public void deleteAllByBookId(String bookId) {

    commentaryRepository.deleteCommentariesByBookId(bookId);

  }

  @Override
  public Optional<Commentary> getById(String id) {
    return commentaryRepository.findById(id);
  }


  @Transactional
  @Override
  public Commentary insertOrUpdate(Commentary commentary) {

    return commentaryRepository.save(commentary);
  }

  @Transactional
  @Override
  public void deleteById(String id) {
    var optional = commentaryRepository.findById(id);
    var commentary = optionalChecker.getFromOptionalOrThrowException(Commentary.class, optional,
        id);

    commentaryRepository.delete(commentary);

  }

}
