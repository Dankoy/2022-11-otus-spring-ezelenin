package ru.dankoy.hw19.core.dto.mapper;

import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import ru.dankoy.hw19.core.domain.Edition;
import ru.dankoy.hw19.core.domain.Work;
import ru.dankoy.hw19.core.dto.EditionDTO;

@Component
public class EditionMapperImpl implements EditionMapper {

  @Override
  public Edition fromDTO(EditionDTO dto) {
    return new Edition(
        dto.getId(),
        new Work(dto.getId()),
        dto.getName(),
        dto.getDescription(),
        dto.getDatePublished(),
        dto.getLanguage(),
        dto.getPages(),
        dto.getPublisher(),
        dto.getCover(),
        dto.getIsbn10(),
        dto.getIsbn13(),
        dto.getDateCreated(),
        dto.getDateModified(),
        dto.getCreatedByUser(),
        dto.getModifiedByUser()
    );
  }

  @Override
  public EditionDTO toDTO(Edition edition) {
    return EditionDTO.builder()
        .id(edition.getId())
        .name(edition.getName())
        .description(edition.getDescription())
        .cover(edition.getCover())
        .isbn10(edition.getIsbn10())
        .isbn13(edition.getIsbn13())
        .pages(edition.getPages())
        .language(edition.getLanguage())
        .publisher(edition.getPublisher())
        .datePublished(edition.getDatePublished())
        .createdByUser(edition.getCreatedByUser())
        .modifiedByUser(edition.getModifiedByUser())
        .dateCreated(edition.getDateCreated())
        .dateModified(edition.getDateModified())
        .workId(edition.getWork().getId())
        .build();
  }

  @Override
  public Set<EditionDTO> toSetDTO(Set<Edition> editions) {

    return editions.stream()
        .map(this::toDTO)
        .collect(Collectors.toSet());

  }

  @Override
  public Set<Edition> fromSetDto(Set<EditionDTO> dtos) {

    return dtos.stream()
        .map(this::fromDTO)
        .collect(Collectors.toSet());

  }
}
