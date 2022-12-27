package ru.dankoy.hw5.core.commands;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.dankoy.hw5.core.domain.Author;
import ru.dankoy.hw5.core.service.author.AuthorService;
import ru.dankoy.hw5.core.service.objectmapper.ObjectMapperService;

@RequiredArgsConstructor
@ShellComponent
public class AuthorCommand {

  private final AuthorService authorService;
  private final ObjectMapperService objectMapperService;


  @ShellMethod(key = {"author-count", "ac"}, value = "Count all authors")
  public String count() {
    Long count = authorService.count();
    return objectMapperService.convertToString(count);
  }

  @ShellMethod(key = {"author-get-by-id", "agbi"}, value = "Get author by id")
  public String getById(@ShellOption long id) {
    Author author = authorService.getById(id);
    return objectMapperService.convertToString(author);
  }


  @ShellMethod(key = {"author-get-all", "aga"}, value = "Get all authors")
  public String getAll() {
    List<Author> authors = authorService.getAll();
    return objectMapperService.convertToString(authors);
  }


  @ShellMethod(key = {"author-insert", "ai"}, value = "Insert new author")
  public String insert(@ShellOption String authorName) {
    long id = authorService.insert(authorName);
    return objectMapperService.convertToString(id);
  }

  @ShellMethod(key = {"author-delete", "ad"}, value = "Delete author by id")
  public String deleteById(@ShellOption long id) {
    authorService.deleteById(id);
    return String.format("Deleted author with id - %d", id);
  }


  @ShellMethod(key = {"author-update", "au"}, value = "Update author")
  public String update(@ShellOption long id, @ShellOption String authorName) {
    Author author = new Author(id, authorName);
    authorService.update(author);
    return String.format("Updated author - %s", objectMapperService.convertToString(author));
  }

}
