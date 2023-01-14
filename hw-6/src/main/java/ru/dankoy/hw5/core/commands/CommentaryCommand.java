package ru.dankoy.hw5.core.commands;


import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.dankoy.hw5.core.domain.Commentary;
import ru.dankoy.hw5.core.service.commentary.CommentaryService;
import ru.dankoy.hw5.core.service.objectmapper.ObjectMapperService;


@RequiredArgsConstructor
@ShellComponent
public class CommentaryCommand {

  private final CommentaryService commentaryService;

  private final ObjectMapperService objectMapperService;


  @ShellMethod(key = {"commentary-get-by-id", "cgbi"}, value = "Get commentary by id")
  public String getById(@ShellOption long id) {
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
  public String getAllByBookId(@ShellOption long bookId) {
    var authors = commentaryService.getAllByBookId(bookId);
    return objectMapperService.convertToString(authors);
  }


  @ShellMethod(key = {"commentary-insert", "ci"}, value = "Insert new commentary")
  public String insert(@ShellOption long bookId, @ShellOption String comment) {

    var commentary = new Commentary(0L, bookId, comment);
    var createdCommentary = commentaryService.insertOrUpdate(commentary);
    return objectMapperService.convertToString(createdCommentary);
  }

  @ShellMethod(key = {"commentary-delete", "cd"}, value = "Delete commentary by id")
  public String deleteById(@ShellOption long id) {
    commentaryService.deleteById(id);
    return String.format("Deleted commentary with id - %d", id);
  }


  @ShellMethod(key = {"commentary-update", "cu"}, value = "Update commentary")
  public String update(@ShellOption long bookId, @ShellOption long commentaryId,
      @ShellOption String commentaryText) {
    var commentary = new Commentary(commentaryId, bookId, commentaryText);
    commentaryService.insertOrUpdate(commentary);
    return String.format("Updated commentary - %s", objectMapperService.convertToString(commentary));
  }


}
