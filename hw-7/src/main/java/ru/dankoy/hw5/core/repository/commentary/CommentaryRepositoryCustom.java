package ru.dankoy.hw5.core.repository.commentary;

import java.util.Optional;
import ru.dankoy.hw5.core.domain.Commentary;

public interface CommentaryRepositoryCustom {

  Optional<Commentary> getById(long id);

}
