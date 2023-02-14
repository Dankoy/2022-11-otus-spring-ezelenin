package ru.dankoy.hw8.core.commands;

import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.dankoy.hw8.core.domain.Genre;
import ru.dankoy.hw8.core.service.genre.GenreService;
import ru.dankoy.hw8.core.service.objectmapper.ObjectMapperService;

@RequiredArgsConstructor
@ShellComponent
public class GenreCommand {

  private final GenreService genreService;
  private final ObjectMapperService objectMapperService;


  @ShellMethod(key = {"genre-update", "gu"}, value = "Update genre")
  public String update(@ShellOption String oldName, @ShellOption String newName) {
    var oldGenre = new Genre(oldName);
    var newGenre = new Genre(newName);
    genreService.update(oldGenre, newGenre);
    return String.format("Updated genre - %s to %s", objectMapperService.convertToString(oldGenre),
        objectMapperService.convertToString(newGenre));
  }

  @ShellMethod(key = {"genre-delete", "gd"}, value = "Delete genre")
  public String delete(@ShellOption String genre) {
    var oldGenre = new Genre(genre);
    genreService.delete(oldGenre);
    return String.format("Deleted genre - %s", objectMapperService.convertToString(oldGenre));
  }

  @ShellMethod(key = {"genre-get-all", "gga"}, value = "Get all distinct genres")
  public String getAll() {
    Set<Genre> genres = genreService.getAllGenres();
    return String.format("All genres - %s", objectMapperService.convertToString(genres));
  }


}
