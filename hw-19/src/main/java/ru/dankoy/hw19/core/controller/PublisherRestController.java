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
import ru.dankoy.hw19.core.dto.publisher.PublisherCreateDTO;
import ru.dankoy.hw19.core.dto.publisher.PublisherFullDTO;
import ru.dankoy.hw19.core.dto.publisher.PublisherUpdateDTO;
import ru.dankoy.hw19.core.exceptions.Entity;
import ru.dankoy.hw19.core.exceptions.EntityNotFoundException;
import ru.dankoy.hw19.core.service.publisher.PublisherService;

@RequiredArgsConstructor
@RestController
public class PublisherRestController {

  private final PublisherService publisherService;


  @GetMapping("/api/v1/publisher")
  public List<PublisherFullDTO> getAll() {

    var found = publisherService.findAll();

    return found.stream().map(PublisherFullDTO::toDTO).toList();

  }

  @GetMapping("/api/v1/publisher/{id}")
  public PublisherFullDTO getById(@PathVariable String id) {

    var found = publisherService.findById(id)
        .orElseThrow(() -> new EntityNotFoundException(id, Entity.PUBLISHER));

    return PublisherFullDTO.toDTO(found);

  }


  @DeleteMapping("/api/v1/publisher/{id}")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public void delete(@PathVariable String id) {

    publisherService.deleteById(id);

  }

  @PostMapping("/api/v1/publisher")
  public PublisherFullDTO create(@Valid @RequestBody PublisherCreateDTO dto) {

    var toCreate = PublisherCreateDTO.fromDTO(dto);
    var created = publisherService.create(toCreate);

    return PublisherFullDTO.toDTO(created);

  }

  @PutMapping("/api/v1/publisher/{id}")
  public PublisherFullDTO update(@PathVariable String id, @Valid @RequestBody PublisherUpdateDTO dto) {

    publisherService.findById(id)
        .orElseThrow(() -> new EntityNotFoundException(id, Entity.PUBLISHER));

    var toUpdate = PublisherUpdateDTO.fromDTO(dto);
    var updated = publisherService.update(toUpdate);

    return PublisherFullDTO.toDTO(updated);

  }


}
