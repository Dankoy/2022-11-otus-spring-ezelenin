package ru.dankoy.hw19.core.service.edition;

import java.util.Optional;
import ru.dankoy.hw19.core.domain.Edition;

public interface EditionService {

  Optional<Edition> findById(String id);

  Edition create(Edition edition);

  void deleteById(String id);

  Edition update(Edition edition);

}
