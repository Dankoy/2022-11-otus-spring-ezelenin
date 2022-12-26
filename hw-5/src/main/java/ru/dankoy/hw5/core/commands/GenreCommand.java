package ru.dankoy.hw5.core.commands;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.dankoy.hw5.core.domain.Genre;
import ru.dankoy.hw5.core.service.objectmapper.ObjectMapperService;
import ru.dankoy.hw5.core.service.genre.GenreService;

@RequiredArgsConstructor
@ShellComponent
public class GenreCommand {

  private final GenreService genreService;
  private final ObjectMapperService objectMapperService;


  @ShellMethod(key = {"genre-count", "gc"}, value = "Count all genre")
  public String count() {
    Long count = genreService.count();
    return objectMapperService.convertToString(count);
  }

  @ShellMethod(key = {"genre-get-by-id", "ggbi"}, value = "Get genre by id")
  public String getById(@ShellOption long id) {
    Genre genre = genreService.getById(id);
    return objectMapperService.convertToString(genre);
  }


  @ShellMethod(key = {"genre-get-all", "gga"}, value = "Get all genre")
  public String getAll() {
    List<Genre> genres = genreService.getAll();
    return objectMapperService.convertToString(genres);
  }


  @ShellMethod(key = {"genre-insert", "gi"}, value = "Insert new genre")
  public String insert(@ShellOption String genreName) {
    long id = genreService.insert(genreName);
    return objectMapperService.convertToString(id);
  }

  @ShellMethod(key = {"genre-delete", "gd"}, value = "Delete genre by id")
  public String deleteById(@ShellOption long id) {
    genreService.deleteById(id);
    return String.format("Deleted genre with id - %d", id);
  }


  @ShellMethod(key = {"genre-update", "gu"}, value = "Update genre")
  public String update(@ShellOption long id, @ShellOption String genreName) {
    Genre genre = new Genre(id, genreName);
    genreService.update(genre);
    return String.format("Updated genre - %s", objectMapperService.convertToString(genre));
  }

}
