package ru.dankoy.hw19.core.service.shelf;

import java.util.Optional;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dankoy.hw19.core.domain.Shelf;
import ru.dankoy.hw19.core.repository.shelf.ShelfRepository;

@RequiredArgsConstructor
@Service
public class ShelfServiceImpl implements ShelfService {

  private final ShelfRepository shelfRepository;

  @Override
  public Optional<Shelf> getShelfById(String id) {
    return shelfRepository.findById(id);
  }

  @Override
  public void deleteShelf(String id) {

    var optionalShelf = shelfRepository.findById(id);
    optionalShelf.ifPresent(shelfRepository::delete);

  }

  @Override
  public Shelf createShelf(Shelf shelf) {

    return shelfRepository.save(shelf);

  }

  @Override
  public Set<Shelf> getShelvesByUserId(String userId) {
    return shelfRepository.findAllByUserId(userId);
  }

  @Override
  public Shelf updateShelf(Shelf shelf) {
    return shelfRepository.save(shelf);
  }
}
