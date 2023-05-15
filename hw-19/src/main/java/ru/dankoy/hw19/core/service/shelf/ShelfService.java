package ru.dankoy.hw19.core.service.shelf;

import java.util.Optional;
import java.util.Set;
import ru.dankoy.hw19.core.domain.Shelf;

public interface ShelfService {

  Optional<Shelf> getById(String id);

  void deleteById(String id);

  Shelf create(Shelf shelf);

  Set<Shelf> findAll();

  Shelf update(Shelf shelf);

}
