package ru.dankoy.hw7.core.repository.commentary;

import java.util.Optional;
import ru.dankoy.hw7.core.domain.Commentary;

public interface CommentaryRepositoryCustom {

  Optional<Commentary> getById(long id);

}
