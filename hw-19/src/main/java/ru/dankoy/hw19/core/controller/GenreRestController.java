package ru.dankoy.hw19.core.controller;


import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.dankoy.hw19.core.domain.Genre;
import ru.dankoy.hw19.core.dto.genre.GenreDTO;
import ru.dankoy.hw19.core.service.genre.GenreService;

@RequiredArgsConstructor
@RestController
public class GenreRestController {

  private final GenreService genreService;


  @GetMapping("/api/v1/genre")
  public Set<Genre> getAll() {

    return genreService.getAllGenres();

  }

  @DeleteMapping("/api/v1/genre")
  public void delete(@RequestParam String name) {

    var genre = new Genre(name);
    genreService.delete(genre);

  }

  @PutMapping("/api/v1/genre")
  public void update(@RequestBody GenreDTO newGenreDto, @RequestParam String oldGenre) {

    var old = new Genre(oldGenre);
    var newGenre = GenreDTO.fromDTO(newGenreDto);

    genreService.update(old, newGenre);
  }

}
