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


  @ShellMethod(key = {"genre-update", "gu"}, value = "Update genre")
  public String update(@ShellOption String oldName, @ShellOption String newName) {
    var oldGenre = new Genre(oldName);
    var newGenre = new Genre(newName);
    genreService.update(oldGenre, newGenre);
    return String.format("Updated genre - %s to %s", objectMapperService.convertToString(oldGenre),
        objectMapperService.convertToString(newGenre));
  }

}
