package ru.dankoy.hw19.core.service.publisher;

import java.util.List;
import java.util.Optional;
import ru.dankoy.hw19.core.domain.Publisher;

public interface PublisherService {

  Optional<Publisher> findById(String id);

  List<Publisher> findAll();

  Publisher create(Publisher publisher);

  void deleteById(String id);

  Publisher update(Publisher publisher);

}
