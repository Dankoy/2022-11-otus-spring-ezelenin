package ru.dankoy.hw8.core.repository.commentary;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.keyvalue.core.KeyValueOperations;
import ru.dankoy.hw8.core.domain.Commentary;

@RequiredArgsConstructor
public class CommentaryRepositoryImpl implements CommentaryRepositoryCustom {

  private final KeyValueOperations keyValueOperations;

  @Override
  public Optional<Commentary> getById(int id) {
    return keyValueOperations.findById(id, Commentary.class);
  }
}
