package ru.dankoy.hw7.core.commands;

import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.dankoy.hw7.core.domain.Genre;
import ru.dankoy.hw7.core.service.genre.GenreService;
import ru.dankoy.hw7.core.service.objectmapper.ObjectMapperService;

@RequiredArgsConstructor
@ShellComponent
public class GenreCommand {

  private final GenreService genreService;
  private final ObjectMapperService objectMapperService;


  @ShellMethod(key = {"genre-count", "gc"}, value = "Count all genre")
  public String count() {
    var count = genreService.count();
    return objectMapperService.convertToString(count);
  }

  @ShellMethod(key = {"genre-get-by-id", "ggbi"}, value = "Get genre by id")
  public String getById(@ShellOption long id) {
    var optional = genreService.getById(id);

    // не уверен, нужно ли в контроллере обрабатывать null или делегировать логику в сервис
    var genre = optional.orElseThrow(
        () -> new EntityNotFoundException(
            String.format("No genre has been found with id - %d", id))
    );

    return objectMapperService.convertToString(genre);
  }


  @ShellMethod(key = {"genre-get-all", "gga"}, value = "Get all genre")
  public String getAll() {
    var genres = genreService.getAll();
    return objectMapperService.convertToString(genres);
  }


  @ShellMethod(key = {"genre-insert", "gi"}, value = "Insert new genre")
  public String insert(@ShellOption String genreName) {
    var genre = new Genre(0L, genreName);
    var inserted = genreService.insertOrUpdate(genre);
    return objectMapperService.convertToString(inserted);
  }

  @ShellMethod(key = {"genre-delete", "gd"}, value = "Delete genre by id")
  public String deleteById(@ShellOption long id) {
    genreService.deleteById(id);
    return String.format("Deleted genre with id - %d", id);
  }


  @ShellMethod(key = {"genre-update", "gu"}, value = "Update genre")
  public String update(@ShellOption long id, @ShellOption String genreName) {
    var genre = new Genre(id, genreName);
    genreService.insertOrUpdate(genre);
    return String.format("Updated genre - %s", objectMapperService.convertToString(genre));
  }

}
