package ru.dankoy.hw19.core.service.edition;

import java.util.Optional;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dankoy.hw19.core.aspects.AddCreatedMetadata;
import ru.dankoy.hw19.core.domain.Edition;
import ru.dankoy.hw19.core.repository.edition.EditionRepository;
import ru.dankoy.hw19.core.service.work.WorkService;


@Service
@RequiredArgsConstructor
public class EditionServiceImpl implements EditionService {

  private final EditionRepository editionRepository;
  private final WorkService workService;

  @Override
  public Set<Edition> findAllByWorkId(String workId) {
    return editionRepository.findAllByWorkId(workId);
  }

  @Override
  public Optional<Edition> findById(String id) {
    return editionRepository.findById(id);
  }

  @Override
  public Edition create(Edition edition) {

    // получает объект работы и обновляет его список изданий
    // todo: перенести в репозиторий
    var work = workService.getById(edition.getWork().getId());

    var createdEdition = editionRepository.save(edition);

    work.ifPresent(work1 -> {
      work1.getEditions().add(createdEdition);
      workService.update(work1);
    });

    return createdEdition;

  }

  @Override
  public void deleteById(String id) {

    var optionalEdition = editionRepository.findById(id);
    optionalEdition.ifPresent(editionRepository::deleteAndCheckNotesShelvesWorks);

  }

  @Override
  @AddCreatedMetadata
  public Edition update(Edition edition) {
    return editionRepository.save(edition);
  }
}
