package ru.dankoy.hw13.core.repository.commentary;

import java.util.Optional;
import ru.dankoy.hw13.core.domain.Commentary;

public interface CommentaryRepositoryCustom {

  Optional<Commentary> getById(long id);

}
