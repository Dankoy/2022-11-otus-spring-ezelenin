package ru.dankoy.hw19.core.controller;


import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.dankoy.hw19.core.dto.commentary.CommentaryCreateDTO;
import ru.dankoy.hw19.core.dto.commentary.CommentaryDTO;
import ru.dankoy.hw19.core.dto.work.WorkCommentaryDTO;
import ru.dankoy.hw19.core.exceptions.Entity;
import ru.dankoy.hw19.core.exceptions.EntityNotFoundException;
import ru.dankoy.hw19.core.service.commentary.CommentaryService;


@RequiredArgsConstructor
@RestController
public class CommentaryRestController {

  private final CommentaryService commentaryService;


  @GetMapping("/api/v1/work/{id}/commentary")
  public List<CommentaryDTO> getAllByBookId(@PathVariable String id) {
    var commentaries = commentaryService.getAllByBookId(id);
    return commentaries.stream().map(CommentaryDTO::toDTO).collect(Collectors.toList());
  }


  @PostMapping("/api/v1/work/{workId}/commentary")
  public CommentaryDTO createCommentaryToWork(@PathVariable String workId,
      @RequestBody CommentaryCreateDTO dto) {

    var work = new WorkCommentaryDTO(workId);
    dto.setWork(work);
    var fromDto = CommentaryCreateDTO.toCommentary(dto);

    var created = commentaryService.insert(fromDto);

    return CommentaryDTO.toDTO(created);

  }


  @GetMapping("/api/v1/commentary/{id}")
  public CommentaryDTO getById(@PathVariable String id) {

    var commentary = commentaryService.getById(id)
        .orElseThrow(() -> new EntityNotFoundException(id, Entity.COMMENTARY));

    return CommentaryDTO.toDTO(commentary);

  }


  @PutMapping("/api/v1/commentary/{id}")
  public CommentaryDTO update(@PathVariable String id,
      @RequestBody CommentaryCreateDTO dto) {

    // Не позволяем менять работу комментария.
    var found = commentaryService.getById(id)
        .orElseThrow(() -> new EntityNotFoundException(id, Entity.COMMENTARY));

    dto.setWork(new WorkCommentaryDTO(found.getWork().getId()));

    var toUpdate = CommentaryCreateDTO.toCommentary(dto);

    var updated = commentaryService.update(toUpdate);

    return CommentaryDTO.toDTO(updated);

  }


  @DeleteMapping("/api/v1/commentary/{id}")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public void delete(@PathVariable String id) {
    commentaryService.deleteById(id);
  }


}
