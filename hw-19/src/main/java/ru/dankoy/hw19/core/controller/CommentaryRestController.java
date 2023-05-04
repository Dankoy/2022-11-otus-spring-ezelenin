package ru.dankoy.hw19.core.controller;


import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.dankoy.hw19.core.dto.CommentaryDTO;
import ru.dankoy.hw19.core.dto.mapper.CommentaryMapper;
import ru.dankoy.hw19.core.service.commentary.ResilienceCommentaryService;


@RequiredArgsConstructor
@RestController
public class CommentaryRestController {

  private final ResilienceCommentaryService commentaryService;

  private final CommentaryMapper commentaryMapper;


  @GetMapping("/api/v1/book/{id}/commentary")
  public List<CommentaryDTO> getAllByBookId(@PathVariable String id)
      throws ExecutionException, InterruptedException {
    var commentaries = commentaryService.getAllByBookId(id);
    return commentaries.get().stream().map(commentaryMapper::toDTO).collect(Collectors.toList());
  }


  @DeleteMapping("/api/v1/commentary/{id}")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public void delete(@PathVariable String id) {
    commentaryService.deleteById(id);
  }


}
