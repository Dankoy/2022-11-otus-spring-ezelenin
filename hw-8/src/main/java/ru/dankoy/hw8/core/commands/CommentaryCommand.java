package ru.dankoy.hw8.core.commands;


import java.util.HashSet;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.dankoy.hw8.core.domain.Author;
import ru.dankoy.hw8.core.domain.Book;
import ru.dankoy.hw8.core.domain.Commentary;
import ru.dankoy.hw8.core.domain.Genre;
import ru.dankoy.hw8.core.exceptions.EntityNotFoundException;
import ru.dankoy.hw8.core.service.book.BookService;
import ru.dankoy.hw8.core.service.commentary.CommentaryService;
import ru.dankoy.hw8.core.service.objectmapper.ObjectMapperService;


@RequiredArgsConstructor
@ShellComponent
public class CommentaryCommand {

  private final CommentaryService commentaryService;
  private final BookService bookService;

  private final ObjectMapperService objectMapperService;


  @ShellMethod(key = {"commentary-get-by-id", "cgbi"}, value = "Get commentary by id")
  public String getById(@ShellOption String id) {
    var optional = commentaryService.getById(id);

    // не уверен, нужно ли в контроллере обрабатывать null или делегировать логику в сервис
    var author = optional.orElseThrow(
        () -> new EntityNotFoundException(
            String.format("No commentary has been found with id - %d", id))
    );

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

    var book = bookService.getById(bookId).orElseThrow(
        () -> new EntityNotFoundException(
            String.format("No commentary has been found with id - %s", bookId))
    );

    // удаляем все комментарии книги из коллекции комментариев
    commentaryService.deleteAllByBookId(bookId);

    // удалям ссылки на удаленные комментарии из объекта книги
    book.getCommentaries().clear();
    var authorIds = book.getAuthors().stream().map(Author::getId).toArray(String[]::new);
    var genreNames = book.getGenres().stream().map(Genre::getName).toArray(String[]::new);

    bookService.update(book, authorIds, genreNames);

    return String.format("Deleted all commentaries for book - %s", bookId);
  }

  @ShellMethod(key = {"commentary-insert", "ci"}, value = "Insert new commentary")
  public String insert(@ShellOption String bookId, @ShellOption String comment) {
    var book = new Book(bookId, null, new HashSet<>(), new HashSet<>(), new HashSet<>());
    var commentary = new Commentary(null, comment, book);
    var createdCommentary = commentaryService.insertOrUpdate(commentary);
    return objectMapperService.convertToString(createdCommentary);
  }

  @ShellMethod(key = {"commentary-delete", "cd"}, value = "Delete commentary by id")
  public String deleteById(@ShellOption String id) {
    commentaryService.deleteById(id);
    return String.format("Deleted commentary with id - %s", id);
  }
//
//
//  @ShellMethod(key = {"commentary-update", "cu"}, value = "Update commentary")
//  public String update(@ShellOption long bookId, @ShellOption long commentaryId,
//      @ShellOption String commentaryText) {
//    var book = new Book(bookId, null, new HashSet<>(), new HashSet<>(), new HashSet<>());
//    var commentary = new Commentary(commentaryId, commentaryText, book);
//    commentaryService.insertOrUpdate(commentary);
//    return String.format("Updated commentary - %s",
//        objectMapperService.convertToString(commentary));
//  }


}
