package ru.dankoy.hw8.core.commands;

import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.dankoy.hw8.core.domain.Author;
import ru.dankoy.hw8.core.service.author.AuthorService;
import ru.dankoy.hw8.core.service.objectmapper.ObjectMapperService;

@RequiredArgsConstructor
@ShellComponent
public class AuthorCommand {

  private final AuthorService authorService;
  private final ObjectMapperService objectMapperService;


  @ShellMethod(key = {"author-count", "ac"}, value = "Count all authors")
  public String count() {
    var count = authorService.count();
    return objectMapperService.convertToString(count);
  }

  @ShellMethod(key = {"author-get-by-id", "agbi"}, value = "Get author by id")
  public String getById(@ShellOption long id) {
    var optional = authorService.getById(id);

    // не уверен, нужно ли в контроллере обрабатывать null или делегировать логику в сервис
    var author = optional.orElseThrow(
        () -> new EntityNotFoundException(
            String.format("No author has been found with id - %d", id))
    );

    return objectMapperService.convertToString(author);
  }


  @ShellMethod(key = {"author-get-all", "aga"}, value = "Get all authors")
  public String getAll() {
    var authors = authorService.getAll();
    return objectMapperService.convertToString(authors);
  }


  @ShellMethod(key = {"author-insert", "ai"}, value = "Insert new author")
  public String insert(@ShellOption String authorName) {
    var newAuthor = new Author(0L, authorName);
    var createdAuthor = authorService.insertOrUpdate(newAuthor);
    return objectMapperService.convertToString(createdAuthor);
  }

  @ShellMethod(key = {"author-delete", "ad"}, value = "Delete author by id")
  public String deleteById(@ShellOption long id) {
    authorService.deleteById(id);
    return String.format("Deleted author with id - %d", id);
  }


  @ShellMethod(key = {"author-update", "au"}, value = "Update author")
  public String update(@ShellOption long id, @ShellOption String authorName) {
    var author = new Author(id, authorName);
    authorService.insertOrUpdate(author);
    return String.format("Updated author - %s", objectMapperService.convertToString(author));
  }

}
