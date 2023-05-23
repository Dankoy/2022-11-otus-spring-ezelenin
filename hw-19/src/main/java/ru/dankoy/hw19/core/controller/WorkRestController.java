package ru.dankoy.hw19.core.controller;


import java.util.List;
import javax.validation.Valid;
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
import ru.dankoy.hw19.core.dto.work.WorkCreateDTO;
import ru.dankoy.hw19.core.dto.work.WorkCreatedDTO;
import ru.dankoy.hw19.core.dto.work.WorkFullDTO;
import ru.dankoy.hw19.core.dto.work.WorkUpdateDTO;
import ru.dankoy.hw19.core.exceptions.Entity;
import ru.dankoy.hw19.core.exceptions.EntityNotFoundException;
import ru.dankoy.hw19.core.service.work.WorkService;


@RequiredArgsConstructor
@RestController
public class WorkRestController {

  private final WorkService workService;

  @GetMapping(value = "/api/v1/work",
      consumes = {"application/json"},
      produces = {"application/json"})
  public List<WorkFullDTO> getAll() {

    var books = workService.findAll();

    return books.stream()
        .map(WorkFullDTO::toDTOWithoutCommentaries)
        .toList();

  }


  @DeleteMapping(value = "/api/v1/work/{id}",
      consumes = {"application/json"},
      produces = {"application/json"})
  @ResponseStatus(value = HttpStatus.ACCEPTED)
  public void delete(@PathVariable String id) {

    workService.deleteById(id);

  }

  @GetMapping(value = "/api/v1/work/{id}",
      consumes = {"application/json"},
      produces = {"application/json"})
  public WorkFullDTO getById(@PathVariable String id) {

    var book = workService.getById(id)
        .orElseThrow(() -> new EntityNotFoundException(id, Entity.BOOK));

    return WorkFullDTO.toDTOWithoutCommentaries(book);

  }


  @PutMapping(value = "/api/v1/work/{id}",
      consumes = {"application/json"},
      produces = {"application/json"})
  public WorkCreatedDTO update(@PathVariable String id, @Valid @RequestBody WorkUpdateDTO dto) {

    var book = WorkUpdateDTO.toWork(dto);

    workService.getById(id)
        .orElseThrow(() -> new EntityNotFoundException(id, Entity.BOOK));

    var updated = workService.update(book);

    return WorkCreatedDTO.toDTOWithoutCommentaries(updated);

  }

  @PostMapping(value = "/api/v1/work",
      consumes = {"application/json"},
      produces = {"application/json"})
  public WorkCreatedDTO create(@Valid @RequestBody WorkCreateDTO dto) {

    var book = WorkCreateDTO.toWork(dto);

    var created = workService.insert(book);

    return WorkCreatedDTO.toDTOWithoutCommentaries(created);

  }

}
