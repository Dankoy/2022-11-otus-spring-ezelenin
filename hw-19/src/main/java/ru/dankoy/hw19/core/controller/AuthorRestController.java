package ru.dankoy.hw19.core.controller;


import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.dankoy.hw19.core.dto.author.AuthorDTO;
import ru.dankoy.hw19.core.exceptions.Entity;
import ru.dankoy.hw19.core.exceptions.EntityNotFoundException;
import ru.dankoy.hw19.core.service.author.AuthorService;

@RestController
@RequiredArgsConstructor
public class AuthorRestController {

  private final AuthorService authorService;


  @GetMapping("/api/v1/author")
  public List<AuthorDTO> getAuthorList() {

    var authors = authorService.getAll();

    return authors.stream()
        .map(AuthorDTO::fromAuthor)
        .collect(Collectors.toList());

  }

  @GetMapping("/api/v1/author/{id}")
  public AuthorDTO getAuthorById(@PathVariable String id) {

    var author = authorService.getById(id)
        .orElseThrow(() -> new EntityNotFoundException(id, Entity.AUTHOR));

    return AuthorDTO.fromAuthor(author);

  }

  @PutMapping("/api/v1/author/{id}")
  public AuthorDTO updateAuthor(@PathVariable String id, @RequestBody AuthorDTO dto) {

    authorService.getById(id)
        .orElseThrow(() -> new EntityNotFoundException(id, Entity.AUTHOR));

    var fromDto = AuthorDTO.fromDTO(dto);
    var updated = authorService.insertOrUpdate(fromDto);

    return AuthorDTO.fromAuthor(updated);

  }

  @PostMapping("/api/v1/author")
  public AuthorDTO createAuthor(@RequestBody AuthorDTO dto) {

    var fromDto = AuthorDTO.fromDTO(dto);
    var created = authorService.insertOrUpdate(fromDto);

    return AuthorDTO.fromAuthor(created);

  }

  @DeleteMapping("/api/v1/author/{id}")
  public void createAuthor(@PathVariable String id) {

    authorService.deleteById(id);

  }

}
