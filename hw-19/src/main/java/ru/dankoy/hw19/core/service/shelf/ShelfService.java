package ru.dankoy.hw19.core.service.shelf;

import java.util.Optional;
import java.util.Set;
import ru.dankoy.hw19.core.domain.Shelf;

public interface ShelfService {

  Optional<Shelf> getShelfById(String id);

  void deleteShelf(String id);

  Shelf createShelf(Shelf shelf);

  Set<Shelf> getShelvesByUserId(String userId);

  Shelf updateShelf(Shelf shelf);

}
