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
import ru.dankoy.hw19.core.dto.shelf.ShelfCreateDTO;
import ru.dankoy.hw19.core.dto.shelf.ShelfCreatedDTO;
import ru.dankoy.hw19.core.dto.shelf.ShelfFullDTO;
import ru.dankoy.hw19.core.dto.shelf.ShelfUpdateDTO;
import ru.dankoy.hw19.core.exceptions.Entity;
import ru.dankoy.hw19.core.exceptions.EntityNotFoundException;
import ru.dankoy.hw19.core.service.shelf.ShelfService;

@RequiredArgsConstructor
@RestController
public class ShelfRestController {

  private final ShelfService shelfService;


  @GetMapping("/api/v1/shelf")
  public List<ShelfFullDTO> getAll() {

    var found = shelfService.findAll();

    return found.stream().map(ShelfFullDTO::toDTO).toList();

  }

  @GetMapping("/api/v1/shelf/{id}")
  public ShelfFullDTO getById(@PathVariable String id) {

    var found = shelfService.getById(id)
        .orElseThrow(() -> new EntityNotFoundException(id, Entity.SHELF));

    return ShelfFullDTO.toDTO(found);

  }


  @DeleteMapping("/api/v1/shelf/{id}")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public void delete(@PathVariable String id) {

    shelfService.deleteById(id);

  }

  @PostMapping("/api/v1/shelf")
  public ShelfCreatedDTO create(@Valid @RequestBody ShelfCreateDTO dto) {

    var toCreate = ShelfCreateDTO.fromDTO(dto);
    var created = shelfService.create(toCreate);

    return ShelfCreatedDTO.toDTO(created);

  }

  @PutMapping("/api/v1/shelf/{id}")
  public ShelfCreatedDTO update(@PathVariable String id, @Valid @RequestBody ShelfUpdateDTO dto) {

    // Проверка на существование полки по id и проверка принадлежности юзеру
    shelfService.getById(id)
        .orElseThrow(() -> new EntityNotFoundException(id, Entity.SHELF));

    var toUpdate = ShelfUpdateDTO.fromDTO(dto);
    var updated = shelfService.update(toUpdate);

    return ShelfCreatedDTO.toDTO(updated);

  }


}
