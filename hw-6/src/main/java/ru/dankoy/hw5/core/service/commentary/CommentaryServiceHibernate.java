package ru.dankoy.hw5.core.service.commentary;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dankoy.hw5.core.dao.commentary.CommentaryDao;
import ru.dankoy.hw5.core.domain.Book;
import ru.dankoy.hw5.core.domain.Commentary;
import ru.dankoy.hw5.core.exceptions.EntityNotFoundException;
import ru.dankoy.hw5.core.service.book.BookService;


@RequiredArgsConstructor
@Service
public class CommentaryServiceHibernate implements CommentaryService {

  private final CommentaryDao commentaryDao;

  private final BookService bookService;


  @Transactional(readOnly = true) // нужен для получения комментариев
  @Override
  public List<Commentary> getAllByBookId(long id) {
    var optional = bookService.getById(id);
    var book = optional.orElseThrow(() -> new EntityNotFoundException(
        String.format("Entity %s has not been found with id - %d", Book.class.getName(), id)));

    return new ArrayList<>(book.getCommentaries());
  }

  @Override
  public Optional<Commentary> getById(long id) {
    return commentaryDao.getById(id);
  }


  @Transactional
  @Override
  public Commentary insertOrUpdate(Commentary commentary) {

    bookService.getById(commentary.getBook().getId()).orElseThrow(
        () -> new EntityNotFoundException(
            String.format("Entity %s has not been found with id - %d", Book.class.getName(),
                commentary.getBook().getId()))
    );

    return commentaryDao.insertOrUpdate(commentary);
  }

  @Transactional
  @Override
  public void deleteById(long id) {
    var optional = commentaryDao.getById(id);
    var commentary = optional.orElseThrow(() -> new EntityNotFoundException(
        String.format("Entity %s has not been found with id - %d", Commentary.class.getName(),
            id)));

    commentaryDao.delete(commentary);

  }

}
