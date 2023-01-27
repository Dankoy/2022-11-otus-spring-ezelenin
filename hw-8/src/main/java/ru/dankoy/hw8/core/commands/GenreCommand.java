package ru.dankoy.hw8.core.commands;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.dankoy.hw8.core.domain.Genre;
import ru.dankoy.hw8.core.service.genre.GenreService;
import ru.dankoy.hw8.core.service.objectmapper.ObjectMapperService;
import ru.dankoy.hw8.core.service.utils.OptionalChecker;

@RequiredArgsConstructor
@ShellComponent
public class GenreCommand {

  private final GenreService genreService;
  private final ObjectMapperService objectMapperService;
  private final OptionalChecker optionalChecker;


  @ShellMethod(key = {"genre-count", "gc"}, value = "Count all genre")
  public String count() {
    var count = genreService.count();
    return objectMapperService.convertToString(count);
  }

  @ShellMethod(key = {"genre-get-by-id", "ggbi"}, value = "Get genre by id")
  public String getById(@ShellOption String id) {
    var optional = genreService.getById(id);

    var genre = optionalChecker.getFromOptionalOrThrowException(Genre.class, optional, id);

    return objectMapperService.convertToString(genre);
  }


  @ShellMethod(key = {"genre-get-all", "gga"}, value = "Get all genre")
  public String getAll() {
    var genres = genreService.getAll();
    return objectMapperService.convertToString(genres);
  }


  @ShellMethod(key = {"genre-insert", "gi"}, value = "Insert new genre")
  public String insert(@ShellOption String genreName) {
    var genre = new Genre(genreName);
    var inserted = genreService.insertOrUpdate(genre);
    return objectMapperService.convertToString(inserted);
  }

  @ShellMethod(key = {"genre-delete", "gd"}, value = "Delete genre by id")
  public String deleteById(@ShellOption String id) {
    genreService.deleteById(id);
    return String.format("Deleted genre with id - %s", id);
  }


  @ShellMethod(key = {"genre-update", "gu"}, value = "Update genre")
  public String update(@ShellOption String id, @ShellOption String genreName) {
    var genre = new Genre(genreName);
    genreService.insertOrUpdate(genre);
    return String.format("Updated genre - %s", objectMapperService.convertToString(genre));
  }

}
