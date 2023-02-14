package ru.dankoy.hw8.core.commands;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.dankoy.hw8.core.domain.Author;
import ru.dankoy.hw8.core.service.author.AuthorService;
import ru.dankoy.hw8.core.service.objectmapper.ObjectMapperService;
import ru.dankoy.hw8.core.service.utils.OptionalChecker;

@RequiredArgsConstructor
@ShellComponent
public class AuthorCommand {

  private final AuthorService authorService;
  private final ObjectMapperService objectMapperService;
  private final OptionalChecker optionalChecker;


  @ShellMethod(key = {"author-count", "ac"}, value = "Count all authors")
  public String count() {
    var count = authorService.count();
    return objectMapperService.convertToString(count);
  }

  @ShellMethod(key = {"author-get-by-id", "agbi"}, value = "Get author by id")
  public String getById(@ShellOption String id) {
    var optional = authorService.getById(id);

    var author = optionalChecker.getFromOptionalOrThrowException(Author.class, optional, id);

    return objectMapperService.convertToString(author);
  }


  @ShellMethod(key = {"author-get-all", "aga"}, value = "Get all authors")
  public String getAll() {
    var authors = authorService.getAll();
    return objectMapperService.convertToString(authors);
  }


  @ShellMethod(key = {"author-insert", "ai"}, value = "Insert new author")
  public String insert(@ShellOption String authorName) {
    var newAuthor = new Author(null, authorName);
    var createdAuthor = authorService.insertOrUpdate(newAuthor);
    return objectMapperService.convertToString(createdAuthor);
  }

  @ShellMethod(key = {"author-delete", "ad"}, value = "Delete author by id")
  public String deleteById(@ShellOption String id) {
    authorService.deleteById(id);
    return String.format("Deleted author with id - %s", id);
  }


  @ShellMethod(key = {"author-update", "au"}, value = "Update author")
  public String update(@ShellOption String id, @ShellOption String authorName) {
    var author = new Author(id, authorName);
    authorService.insertOrUpdate(author);
    return String.format("Updated author - %s", objectMapperService.convertToString(author));
  }

}
