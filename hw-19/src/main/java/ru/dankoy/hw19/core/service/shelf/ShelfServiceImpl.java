package ru.dankoy.hw19.core.service.shelf;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.dankoy.hw19.core.aspects.AddCreatedMetadata;
import ru.dankoy.hw19.core.aspects.AddCurrentUser;
import ru.dankoy.hw19.core.domain.Shelf;
import ru.dankoy.hw19.core.domain.User;
import ru.dankoy.hw19.core.repository.shelf.ShelfRepository;

@RequiredArgsConstructor
@Service
public class ShelfServiceImpl implements ShelfService {

  private final ShelfRepository shelfRepository;

  @Override
  public Optional<Shelf> getById(String id) {

    var user = (User) SecurityContextHolder.getContext()
        .getAuthentication()
        .getPrincipal();

    // костыльная секурность
    return shelfRepository.findByIdAndUserId(id, user.getId());
  }

  @Override
  public void deleteById(String id) {

    // удаляется только если у юзера найдена полка
    // Чужие полки удалить нельзя
    var user = (User) SecurityContextHolder.getContext()
        .getAuthentication()
        .getPrincipal();

    var optionalShelf = shelfRepository.findByIdAndUserId(id, user.getId());
    optionalShelf.ifPresent(shelfRepository::delete);

  }

  @Override
  @AddCurrentUser
  @AddCreatedMetadata
  public Shelf create(Shelf shelf) {
    return shelfRepository.save(shelf);

  }

  @PostFilter(value = "filterObject.user.getId() == authentication.principal.id")
  @Override
  public Set<Shelf> findAll() {
    // Фильтруются полки по id юзера с помощью пост фильтра
    // Юзер получит только свои полки
    return new HashSet<>(shelfRepository.findAll());
  }

  @Override
  @AddCurrentUser
  @AddCreatedMetadata
  public Shelf update(Shelf shelf) {
    return shelfRepository.save(shelf);
  }
}
