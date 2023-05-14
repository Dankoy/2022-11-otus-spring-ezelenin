package ru.dankoy.hw19.core.dto.mappers;

import java.util.Set;
import ru.dankoy.hw19.core.domain.Edition;
import ru.dankoy.hw19.core.dto.edition.EditionDTO;

public interface EditionMapper {

  Edition fromDTO(EditionDTO dto);

  EditionDTO toDTO(Edition edition);

  Set<EditionDTO> toSetDTO(Set<Edition> editions);

  Set<Edition> fromSetDto(Set<EditionDTO> dtos);
}
