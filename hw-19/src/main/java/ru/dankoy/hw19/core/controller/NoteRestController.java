package ru.dankoy.hw19.core.controller;


import java.util.List;
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
import ru.dankoy.hw19.core.dto.note.NoteCreateDTO;
import ru.dankoy.hw19.core.dto.note.NoteCreatedDTO;
import ru.dankoy.hw19.core.dto.note.NoteFullDTO;
import ru.dankoy.hw19.core.dto.note.NoteUpdateDTO;
import ru.dankoy.hw19.core.exceptions.Entity;
import ru.dankoy.hw19.core.exceptions.EntityNotFoundException;
import ru.dankoy.hw19.core.service.note.NoteService;

@RequiredArgsConstructor
@RestController
public class NoteRestController {

  private final NoteService noteService;


  @GetMapping("/api/v1/note")
  public List<NoteFullDTO> getAll() {

    var found = noteService.findAll();

    return found.stream().map(NoteFullDTO::toDTO).toList();

  }

  @GetMapping("/api/v1/note/{id}")
  public NoteFullDTO getById(@PathVariable String id) {

    var found = noteService.findById(id)
        .orElseThrow(() -> new EntityNotFoundException(id, Entity.NOTE));

    return NoteFullDTO.toDTO(found);

  }


  @DeleteMapping("/api/v1/note/{id}")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public void delete(@PathVariable String id) {

    noteService.deleteById(id);

  }

  @PostMapping("/api/v1/edition/{editionId}/note")
  public NoteCreatedDTO create(@PathVariable String editionId, @RequestBody NoteCreateDTO dto) {

    var optionalAlreadyCreated = noteService.findByEditionId(editionId);

    if (optionalAlreadyCreated.isPresent()) {
      throw new IllegalStateException(
          String.format("Note for edition '%s' already exists", editionId));
    }

    var toCreate = NoteCreateDTO.fromDTO(dto);
    var created = noteService.create(toCreate);

    return NoteCreatedDTO.toDTO(created);

  }

  @PutMapping("/api/v1/note/{id}")
  public NoteCreatedDTO update(@PathVariable String id, @RequestBody NoteUpdateDTO dto) {

    // Проверка на существование полки по id и проверка принадлежности юзеру
    // если была попытка обновить заметку не принадлежащую юзеру, то выбрасывается ошибка
    // иначе обновляем
    noteService.findById(id)
        .orElseThrow(() -> new EntityNotFoundException(id, Entity.NOTE));

    var toUpdate = NoteUpdateDTO.fromDTO(dto);
    var updated = noteService.update(toUpdate);

    return NoteCreatedDTO.toDTO(updated);

  }


  // Нужно ли оно вообще?
  @GetMapping(value = "/api/v1/edition/{editionId}/note", produces = {"application/json"})
  public NoteFullDTO getByEdition(@PathVariable String editionId) {

    var note = noteService.findByEditionId(editionId);

    // Если объект не найден, то возвращает пустой json
    return note.map(NoteFullDTO::toDTO)
        .orElseGet(NoteFullDTO::new);

  }


}
