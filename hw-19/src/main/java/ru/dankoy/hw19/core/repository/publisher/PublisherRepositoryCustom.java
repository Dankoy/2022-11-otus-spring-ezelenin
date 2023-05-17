package ru.dankoy.hw19.core.repository.publisher;

import ru.dankoy.hw19.core.domain.Publisher;

public interface PublisherRepositoryCustom {

  void deleteAndCheckEditions(Publisher publisher);
}
