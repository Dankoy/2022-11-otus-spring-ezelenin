package ru.dankoy.hw19.core.controller;


import java.util.Set;
import java.util.stream.Collectors;
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
import ru.dankoy.hw19.core.dto.edition.EditionCreateDTO;
import ru.dankoy.hw19.core.dto.edition.EditionCreatedDTO;
import ru.dankoy.hw19.core.dto.edition.EditionFullDTO;
import ru.dankoy.hw19.core.dto.edition.EditionUpdateDTO;
import ru.dankoy.hw19.core.exceptions.Entity;
import ru.dankoy.hw19.core.exceptions.EntityNotFoundException;
import ru.dankoy.hw19.core.service.edition.EditionService;

@RequiredArgsConstructor
@RestController
public class EditionRestController {

  private final EditionService editionService;

  @GetMapping("/api/v1/edition/{id}")
  public EditionFullDTO getById(@PathVariable String id) {

    var found = editionService.findById(id)
        .orElseThrow(() -> new EntityNotFoundException(id, Entity.EDITION));

    return EditionFullDTO.toDTO(found);

  }


  @DeleteMapping("/api/v1/edition/{id}")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public void delete(@PathVariable String id) {

    editionService.deleteById(id);

  }

  @PostMapping("/api/v1/work/{workId}/edition")
  public EditionCreatedDTO create(@PathVariable String workId, @Valid @RequestBody EditionCreateDTO dto) {

    dto.setWorkId(workId);

    var toCreate = EditionCreateDTO.fromDTO(dto);
    var created = editionService.create(toCreate);

    return EditionCreatedDTO.toDTO(created);

  }

  @GetMapping("/api/v1/work/{workId}/edition")
  public Set<EditionFullDTO> getAllByWork(@PathVariable String workId) {

    var found = editionService.findAllByWorkId(workId);

    return found.stream().map(EditionFullDTO::toDTO).collect(Collectors.toSet());

  }

  @PutMapping("/api/v1/edition/{id}")
  public EditionCreatedDTO update(@PathVariable String id, @Valid @RequestBody EditionUpdateDTO dto) {

    //todo: если издание биндится к другой работе надо обновлять работы и старую и новую

    editionService.findById(id)
        .orElseThrow(() -> new EntityNotFoundException(id, Entity.EDITION));

    var toUpdate = EditionUpdateDTO.fromDTO(dto);
    var updated = editionService.update(toUpdate);

    return EditionCreatedDTO.toDTO(updated);

  }


}
