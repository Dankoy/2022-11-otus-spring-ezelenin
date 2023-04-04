package ru.dankoy.hw16.core.repository.commentary;

import java.util.Optional;
import ru.dankoy.hw16.core.domain.Commentary;

public interface CommentaryRepositoryCustom {

  Optional<Commentary> getById(long id);

}
