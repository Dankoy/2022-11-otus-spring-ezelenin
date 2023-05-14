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
import ru.dankoy.hw19.core.dto.work.WorkDTO;
import ru.dankoy.hw19.core.dto.mappers.BookMapper;
import ru.dankoy.hw19.core.exceptions.Entity;
import ru.dankoy.hw19.core.exceptions.EntityNotFoundException;
import ru.dankoy.hw19.core.service.work.WorkService;


@RequiredArgsConstructor
@RestController
public class WorkRestController {

  private final WorkService workService;
  private final BookMapper bookMapper;

  @GetMapping(value = "/api/v1/work",
      consumes = {"application/json"},
      produces = {"application/json"})
  public List<WorkDTO> getAll() {

    var books = workService.findAll();

    return books.stream()
        .map(bookMapper::toDTOWithoutCommentaries)
        .collect(Collectors.toList());

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
  public WorkDTO getById(@PathVariable String id) {

    var book = workService.getById(id)
        .orElseThrow(() -> new EntityNotFoundException(id, Entity.BOOK));

    return bookMapper.toDTOWithoutCommentaries(book);

  }


  @PutMapping(value = "/api/v1/work/{id}",
      consumes = {"application/json"},
      produces = {"application/json"})
  public WorkDTO update(@PathVariable String id, @RequestBody WorkDTO workDTO) {

    var book = bookMapper.toBook(workDTO);

    workService.getById(id)
        .orElseThrow(() -> new EntityNotFoundException(id, Entity.BOOK));

    var updated = workService.insertOrUpdate(book);

    return bookMapper.toDTOWithoutCommentaries(updated);

  }

  @PostMapping(value = "/api/v1/work",
      consumes = {"application/json"},
      produces = {"application/json"})
  public WorkDTO create(@RequestBody WorkDTO workDTO) {

    var book = bookMapper.toBook(workDTO);

    var created = workService.insertOrUpdate(book);

    return bookMapper.toDTOWithoutCommentaries(created);

  }

}
