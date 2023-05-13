package ru.dankoy.hw19.core.service.edition;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dankoy.hw19.core.domain.Edition;
import ru.dankoy.hw19.core.repository.edition.EditionRepository;


@Service
@RequiredArgsConstructor
public class EditionServiceImpl implements EditionService {

  private final EditionRepository editionRepository;

  @Override
  public Optional<Edition> findById(String id) {
    return editionRepository.findById(id);
  }

  @Override
  public Edition create(Edition edition) {
    return editionRepository.save(edition);
  }

  @Override
  public void deleteById(String id) {
    var optionalEdition = editionRepository.findById(id);
    optionalEdition.ifPresent(editionRepository::delete);
  }

  @Override
  public Edition update(Edition edition) {
    return editionRepository.save(edition);
  }
}
