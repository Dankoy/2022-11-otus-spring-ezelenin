package ru.dankoy.hw12.core.repository.commentary;

import java.util.Optional;
import ru.dankoy.hw12.core.domain.Commentary;

public interface CommentaryRepositoryCustom {

  Optional<Commentary> getById(long id);

}
