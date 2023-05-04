package ru.dankoy.hw11.core.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.dankoy.hw11.core.dto.CommentaryDTO;
import ru.dankoy.hw11.core.dto.mapper.CommentaryMapper;
import ru.dankoy.hw11.core.repository.commentary.CommentaryRepository;

@RequiredArgsConstructor
@RestController
public class CommentaryReactiveRestController {

  private final CommentaryRepository repository;

  private final CommentaryMapper commentaryMapper;


  @GetMapping("/api/v1/book/{id}/commentary")
  public Flux<CommentaryDTO> getAllByBookId(@PathVariable Mono<String> id) {
    return repository.findAllByBookId(id)
        .map(commentaryMapper::toDTO);
  }


  @DeleteMapping("/api/v1/commentary/{id}")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public Mono<Void> delete(@PathVariable String id) {
    return repository.deleteById(id);
  }

}
