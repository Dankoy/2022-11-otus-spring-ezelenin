package ru.dankoy.hw19.core.repository.edition;

import ru.dankoy.hw19.core.domain.Edition;

public interface EditionRepositoryCustom {

  void deleteAndCheckNotesShelvesWorks(Edition edition);

}
