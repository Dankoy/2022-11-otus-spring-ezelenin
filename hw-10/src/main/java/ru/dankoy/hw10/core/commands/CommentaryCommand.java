package ru.dankoy.hw10.core.commands;


import java.util.HashSet;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.dankoy.hw10.core.domain.Book;
import ru.dankoy.hw10.core.domain.Commentary;
import ru.dankoy.hw10.core.service.book.BookService;
import ru.dankoy.hw10.core.service.commentary.CommentaryService;
import ru.dankoy.hw10.core.service.objectmapper.ObjectMapperService;
import ru.dankoy.hw10.core.service.utils.OptionalChecker;


@RequiredArgsConstructor
@ShellComponent
public class CommentaryCommand {

  private final CommentaryService commentaryService;
  private final BookService bookService;

  private final ObjectMapperService objectMapperService;

  private final OptionalChecker optionalChecker;


  @ShellMethod(key = {"commentary-get-by-id", "cgbi"}, value = "Get commentary by id")
  public String getById(@ShellOption String id) {
    var optional = commentaryService.getById(id);

    var author = optionalChecker.getFromOptionalOrThrowException(Commentary.class, optional, id);

    return objectMapperService.convertToString(author);
  }


  @ShellMethod(key = {"commentary-get-all-by-book",
      "cgabb"}, value = "Get all commentaries for book")
  public String getAllByBookId(@ShellOption String bookId) {
    var commentaries = commentaryService.getAllByBookId(bookId);
    return objectMapperService.convertToString(commentaries);
  }

  @ShellMethod(key = {"commentary-delete-all-by-book-id",
      "cdabb"}, value = "Delete all commentaries for book")
  public String deleteAllByBookId(@ShellOption String bookId) {

    // удаляем все комментарии книги из коллекции комментариев
    commentaryService.deleteAllByBookId(bookId);

    return String.format("Deleted all commentaries for book - %s", bookId);
  }

  @ShellMethod(key = {"commentary-insert", "ci"}, value = "Insert new commentary")
  public String insert(@ShellOption String bookId, @ShellOption String comment) {
    var book = new Book(bookId, null, new HashSet<>(), new HashSet<>());
    var commentary = new Commentary(null, comment, book);
    var createdCommentary = commentaryService.insertOrUpdate(commentary);
    return objectMapperService.convertToString(createdCommentary);
  }

  @ShellMethod(key = {"commentary-delete", "cd"}, value = "Delete commentary by id")
  public String deleteById(@ShellOption String id) {
    commentaryService.deleteById(id);
    return String.format("Deleted commentary with id - %s", id);
  }

  @ShellMethod(key = {"commentary-update", "cu"}, value = "Update commentary")
  public String update(@ShellOption String bookId, @ShellOption String commentaryId,
      @ShellOption String commentaryText) {
    var book = new Book(bookId, null, new HashSet<>(), new HashSet<>());
    var commentary = new Commentary(commentaryId, commentaryText, book);
    commentaryService.insertOrUpdate(commentary);
    return String.format("Updated commentary - %s",
        objectMapperService.convertToString(commentary));
  }


}
