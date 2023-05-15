package ru.dankoy.hw19.core.service.publisher;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dankoy.hw19.core.aspects.AddCreatedMetadata;
import ru.dankoy.hw19.core.domain.Publisher;
import ru.dankoy.hw19.core.repository.publisher.PublisherRepository;


@Service
@RequiredArgsConstructor
public class PublisherServiceImpl implements PublisherService {

  private final PublisherRepository publisherRepository;

  @Override
  public Optional<Publisher> findById(String id) {
    return publisherRepository.findById(id);
  }

  @Override
  public List<Publisher> findAll() {
    return publisherRepository.findAll();
  }

  @Override
  public Publisher create(Publisher publisher) {
    return publisherRepository.save(publisher);
  }

  @Override
  public void deleteById(String id) {
    var optionalPublisher = publisherRepository.findById(id);
    optionalPublisher.ifPresent(publisherRepository::delete);
  }

  @Override
  @AddCreatedMetadata
  public Publisher update(Publisher publisher) {
    return publisherRepository.save(publisher);
  }
}
