package ru.dankoy.hw8.core.repository.commentary;

import org.springframework.data.repository.CrudRepository;
import ru.dankoy.hw8.core.domain.Commentary;

public interface CommentaryRepository extends CrudRepository<Commentary, String> {

}
