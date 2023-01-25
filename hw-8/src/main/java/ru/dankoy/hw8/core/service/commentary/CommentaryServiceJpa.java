package ru.dankoy.hw8.core.service.commentary;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dankoy.hw8.core.domain.Book;
import ru.dankoy.hw8.core.domain.Commentary;
import ru.dankoy.hw8.core.exceptions.EntityNotFoundException;
import ru.dankoy.hw8.core.repository.commentary.CommentaryRepository;
import ru.dankoy.hw8.core.service.book.BookService;


@RequiredArgsConstructor
@Service
public class CommentaryServiceJpa implements CommentaryService {

  private final CommentaryRepository commentaryRepository;

  private final BookService bookService;


  @Transactional(readOnly = true) // нужен для получения комментариев
  @Override
  public List<Commentary> getAllByBookId(String id) {
    var optional = bookService.getById(id);
    var book = optional.orElseThrow(() -> new EntityNotFoundException(
        String.format("Entity %s has not been found with id - %d", Book.class.getName(), id)));

    return new ArrayList<>(book.getCommentaries());
  }

  @Override
  public Optional<Commentary> getById(String id) {
    return commentaryRepository.findById(id);
  }


//  @Override
//  public Commentary insertOrUpdate(Commentary commentary) {
//
//    var commentary = bookService.g
//
//    return commentaryRepository.save(commentary);
//  }
//
//  @Override
//  public void deleteById(String id) {
//    var optional = commentaryRepository.getById(id);
//    var commentary = optional.orElseThrow(() -> new EntityNotFoundException(
//        String.format("Entity %s has not been found with id - %d", Commentary.class.getName(),
//            id)));
//
//    commentaryRepository.delete(commentary);
//
//  }

}
