package ru.dankoy.hw8.core.service.commentary;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dankoy.hw8.core.domain.Author;
import ru.dankoy.hw8.core.domain.Book;
import ru.dankoy.hw8.core.domain.Commentary;
import ru.dankoy.hw8.core.domain.Genre;
import ru.dankoy.hw8.core.exceptions.EntityNotFoundException;
import ru.dankoy.hw8.core.repository.commentary.CommentaryRepository;
import ru.dankoy.hw8.core.service.book.BookService;


@RequiredArgsConstructor
@Service
public class CommentaryServiceMongo implements CommentaryService {

  private final CommentaryRepository commentaryRepository;

  private final BookService bookService;


  @Transactional(readOnly = true) // нужен для получения комментариев
  @Override
  public List<Commentary> getAllByBookId(String id) {
    var optional = bookService.getById(id);
    var book = optional.orElseThrow(() -> new EntityNotFoundException(
        String.format("Entity %s has not been found with id - %s", Book.class.getName(), id)));

    return new ArrayList<>(book.getCommentaries());
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


  @Override
  public Commentary insertOrUpdate(Commentary commentary) {
    return commentaryRepository.save(commentary);
  }

  @Override
  public void deleteById(String id) {
    var optional = commentaryRepository.findById(id);
    var commentary = optional.orElseThrow(() -> new EntityNotFoundException(
        String.format("Entity %s has not been found with id - %s", Commentary.class.getName(),
            id)));

    var book = bookService.getById(commentary.getBook().getId());
    book.ifPresent(b -> {
      b.getCommentaries().remove(commentary);
    });

    bookService

    commentaryRepository.delete(commentary);

  }

}
