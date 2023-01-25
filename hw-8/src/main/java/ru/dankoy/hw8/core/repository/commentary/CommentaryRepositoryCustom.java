package ru.dankoy.hw8.core.repository.commentary;

import java.util.Optional;
import ru.dankoy.hw8.core.domain.Commentary;

public interface CommentaryRepositoryCustom {

  Optional<Commentary> getById(int id);

}
