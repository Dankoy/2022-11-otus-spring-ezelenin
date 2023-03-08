package ru.dankoy.hw12.core.service.commentary;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dankoy.hw12.core.domain.Commentary;
import ru.dankoy.hw12.core.exceptions.LibraryElement;
import ru.dankoy.hw12.core.exceptions.EntityNotFoundException;
import ru.dankoy.hw12.core.repository.commentary.CommentaryRepository;
import ru.dankoy.hw12.core.service.book.BookService;


@RequiredArgsConstructor
@Service
public class CommentaryServiceJpa implements CommentaryService {

  private final CommentaryRepository commentaryRepository;

  private final BookService bookService;


  @Transactional(readOnly = true) // нужен для получения комментариев
  @Override
  public List<Commentary> getAllByBookId(long id) {
    var optional = bookService.getById(id);
    var book = optional.orElseThrow(() -> new EntityNotFoundException(id, LibraryElement.BOOK));

    return new ArrayList<>(book.getCommentaries());
  }

  @Override
  public Optional<Commentary> getById(long id) {
    return commentaryRepository.getById(id);
  }


  @Override
  public Commentary insertOrUpdate(Commentary commentary) {

    var book = bookService.getById(commentary.getBook().getId())
        .orElseThrow(() -> new EntityNotFoundException(commentary.getBook().getId(), LibraryElement.BOOK));

    commentary.setBook(book);

    return commentaryRepository.save(commentary);
  }

  @Override
  public void deleteById(long id) {
    var optional = commentaryRepository.getById(id);
    optional.ifPresent(commentaryRepository::delete);
  }

}
