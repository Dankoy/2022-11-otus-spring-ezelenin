package ru.dankoy.hw8.core.service.commentary;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dankoy.hw8.core.domain.Author;
import ru.dankoy.hw8.core.domain.Book;
import ru.dankoy.hw8.core.domain.Commentary;
import ru.dankoy.hw8.core.domain.Genre;
import ru.dankoy.hw8.core.repository.commentary.CommentaryRepository;
import ru.dankoy.hw8.core.service.book.BookService;
import ru.dankoy.hw8.core.service.utils.OptionalChecker;


@RequiredArgsConstructor
@Service
public class CommentaryServiceMongo implements CommentaryService {

  private final CommentaryRepository commentaryRepository;

  private final BookService bookService;

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


  @Transactional(readOnly = true)
  @Override
  public Commentary insertOrUpdate(Commentary commentary) {

    var bookOptional = bookService.getById(commentary.getBook().getId());
    var book = optionalChecker.getFromOptionalOrThrowException(Book.class, bookOptional,
        commentary.getBook().getId());

    var inserted = commentaryRepository.save(commentary);

    // если такого комментария нет, то добавляем в массив
    if (book.getCommentaries().isEmpty()) {
      book.getCommentaries().add(commentary);
    }

    var authorIds = book.getAuthors().stream().map(Author::getId).toArray(String[]::new);
    var genreNames = book.getGenres().stream().map(Genre::getName).toArray(String[]::new);

    bookService.update(book, authorIds, genreNames);

    return inserted;
  }

  @Transactional
  @Override
  public void deleteById(String id) {
    var optional = commentaryRepository.findById(id);
    var commentary = optionalChecker.getFromOptionalOrThrowException(Commentary.class, optional,
        id);

    var bookOptional = bookService.getById(commentary.getBook().getId());
    var book = optionalChecker.getFromOptionalOrThrowException(Book.class, bookOptional,
        commentary.getBook().getId());

    book.getCommentaries().remove(commentary);

    var authorIds = book.getAuthors().stream().map(Author::getId).toArray(String[]::new);
    var genreNames = book.getGenres().stream().map(Genre::getName).toArray(String[]::new);

    bookService.update(book, authorIds, genreNames);

    commentaryRepository.delete(commentary);

  }

}
