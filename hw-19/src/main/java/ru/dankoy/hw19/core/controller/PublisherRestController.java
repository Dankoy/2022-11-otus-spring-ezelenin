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
import ru.dankoy.hw19.core.dto.publisher.PublisherDTO;
import ru.dankoy.hw19.core.exceptions.Entity;
import ru.dankoy.hw19.core.exceptions.EntityNotFoundException;
import ru.dankoy.hw19.core.service.publisher.PublisherService;

@RequiredArgsConstructor
@RestController
public class PublisherRestController {

  private final PublisherService publisherService;


  @GetMapping("/api/v1/publisher")
  public List<PublisherDTO> getAll() {

    var found = publisherService.findAll();

    return found.stream().map(PublisherDTO::toDTO).collect(Collectors.toList());

  }

  @GetMapping("/api/v1/publisher/{id}")
  public PublisherDTO getById(@PathVariable String id) {

    var found = publisherService.findById(id)
        .orElseThrow(() -> new EntityNotFoundException(id, Entity.PUBLISHER));

    return PublisherDTO.toDTO(found);

  }


  @DeleteMapping("/api/v1/publisher/{id}")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public void delete(@PathVariable String id) {

    publisherService.deleteById(id);

  }

  @PostMapping("/api/v1/publisher")
  public PublisherDTO create(@RequestBody PublisherDTO dto) {

    var toCreate = PublisherDTO.fromDTO(dto);
    var created = publisherService.create(toCreate);

    return PublisherDTO.toDTO(created);

  }

  @PutMapping("/api/v1/publisher/{id}")
  public PublisherDTO update(@PathVariable String id, @RequestBody PublisherDTO dto) {

    publisherService.findById(id)
        .orElseThrow(() -> new EntityNotFoundException(id, Entity.PUBLISHER));

    var toUpdate = PublisherDTO.fromDTO(dto);
    var updated = publisherService.update(toUpdate);

    return PublisherDTO.toDTO(updated);

  }


}
