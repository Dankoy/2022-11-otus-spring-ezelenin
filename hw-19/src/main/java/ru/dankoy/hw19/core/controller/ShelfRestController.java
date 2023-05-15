package ru.dankoy.hw19.core.controller;


import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.dankoy.hw19.core.domain.User;
import ru.dankoy.hw19.core.dto.shelf.ShelfDTO;
import ru.dankoy.hw19.core.exceptions.Entity;
import ru.dankoy.hw19.core.exceptions.EntityNotFoundException;
import ru.dankoy.hw19.core.service.shelf.ShelfService;

@RequiredArgsConstructor
@RestController
public class ShelfRestController {

  private final ShelfService shelfService;


  @GetMapping("/api/v1/shelf")
  public List<ShelfDTO> getAll() {

    var user = (User) SecurityContextHolder.getContext()
        .getAuthentication()
        .getPrincipal();

    var found = shelfService.getShelvesByUserId(user.getId());

    return found.stream().map(ShelfDTO::toDTO).collect(Collectors.toList());

  }

  @GetMapping("/api/v1/shelf/{id}")
  public ShelfDTO getById(@PathVariable String id) {

    var found = shelfService.getShelfById(id)
        .orElseThrow(() -> new EntityNotFoundException(id, Entity.SHELF));

    return ShelfDTO.toDTO(found);

  }


  @DeleteMapping("/api/v1/shelf/{id}")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public void delete(@PathVariable String id) {

    shelfService.deleteShelf(id);

  }

  @PostMapping("/api/v1/shelf")
  public ShelfDTO create(@RequestBody ShelfDTO dto) {

    var toCreate = ShelfDTO.fromDTO(dto);
    var created = shelfService.createShelf(toCreate);

    return ShelfDTO.toDTO(created);

  }

  @PutMapping("/api/v1/shelf/{id}")
  public ShelfDTO update(@PathVariable String id, @RequestBody ShelfDTO dto) {

    shelfService.getShelfById(id)
        .orElseThrow(() -> new EntityNotFoundException(id, Entity.SHELF));

    var toUpdate = ShelfDTO.fromDTO(dto);
    var updated = shelfService.updateShelf(toUpdate);

    return ShelfDTO.toDTO(updated);

  }


}
