package ru.dankoy.hw19.core.controller;


import java.util.Set;
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
import ru.dankoy.hw19.core.dto.edition.EditionDTO;
import ru.dankoy.hw19.core.exceptions.Entity;
import ru.dankoy.hw19.core.exceptions.EntityNotFoundException;
import ru.dankoy.hw19.core.service.edition.EditionService;

@RequiredArgsConstructor
@RestController
public class EditionRestController {

  private final EditionService editionService;

  @GetMapping("/api/v1/edition/{id}")
  public EditionDTO getById(@PathVariable String id) {

    var found = editionService.findById(id)
        .orElseThrow(() -> new EntityNotFoundException(id, Entity.EDITION));

    return EditionDTO.toDTO(found);

  }


  @DeleteMapping("/api/v1/edition/{id}")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public void delete(@PathVariable String id) {

    editionService.deleteById(id);

  }

  @PostMapping("/api/v1/work/{workId}/edition")
  public EditionDTO create(@PathVariable String workId, @RequestBody EditionDTO dto) {

    dto.setWorkId(workId);

    var toCreate = EditionDTO.fromDTO(dto);
    var created = editionService.create(toCreate);

    return EditionDTO.toDTO(created);

  }

  @GetMapping("/api/v1/work/{workId}/edition")
  public Set<EditionDTO> getAllByWork(@PathVariable String workId) {

    var found = editionService.findAllByWorkId(workId);

    return found.stream().map(EditionDTO::toDTO).collect(Collectors.toSet());

  }

  @PutMapping("/api/v1/edition/{id}")
  public EditionDTO update(@PathVariable String id, @RequestBody EditionDTO dto) {

    editionService.findById(id)
        .orElseThrow(() -> new EntityNotFoundException(id, Entity.EDITION));

    var toUpdate = EditionDTO.fromDTO(dto);
    var updated = editionService.update(toUpdate);

    return EditionDTO.toDTO(updated);

  }


}
