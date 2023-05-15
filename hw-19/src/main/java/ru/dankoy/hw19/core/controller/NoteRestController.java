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
import ru.dankoy.hw19.core.dto.note.NoteDTO;
import ru.dankoy.hw19.core.exceptions.Entity;
import ru.dankoy.hw19.core.exceptions.EntityNotFoundException;
import ru.dankoy.hw19.core.service.note.NoteService;

@RequiredArgsConstructor
@RestController
public class NoteRestController {

  private final NoteService noteService;


  @GetMapping("/api/v1/note")
  public List<NoteDTO> getAll() {

    var found = noteService.findAll();

    return found.stream().map(NoteDTO::toDTO).collect(Collectors.toList());

  }

  @GetMapping("/api/v1/note/{id}")
  public NoteDTO getById(@PathVariable String id) {

    var found = noteService.findById(id)
        .orElseThrow(() -> new EntityNotFoundException(id, Entity.NOTE));

    return NoteDTO.toDTO(found);

  }


  @DeleteMapping("/api/v1/note/{id}")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public void delete(@PathVariable String id) {

    noteService.deleteById(id);

  }

  @PostMapping("/api/v1/note")
  public NoteDTO create(@RequestBody NoteDTO dto) {

    var toCreate = NoteDTO.fromDTO(dto);
    var created = noteService.create(toCreate);

    return NoteDTO.toDTO(created);

  }

  @PutMapping("/api/v1/note/{id}")
  public NoteDTO update(@PathVariable String id, @RequestBody NoteDTO dto) {

    // Проверка на существование полки по id и проверка принадлежности юзеру
    noteService.findById(id)
        .orElseThrow(() -> new EntityNotFoundException(id, Entity.NOTE));

    var toUpdate = NoteDTO.fromDTO(dto);
    var updated = noteService.update(toUpdate);

    return NoteDTO.toDTO(updated);

  }


  // Нужно ли оно вообще?
  @GetMapping(value = "/api/v1/edition/{editionId}/note", produces= {"application/json"})
  public NoteDTO getByEdition(@PathVariable String editionId) {

    var note = noteService.findByEditionId(editionId);

    // Если объект не найден, то возвращает пустой json
    return note.map(NoteDTO::toDTO)
        .orElseGet(NoteDTO::new);

  }


}
