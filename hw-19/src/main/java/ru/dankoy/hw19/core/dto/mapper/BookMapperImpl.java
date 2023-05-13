package ru.dankoy.hw19.core.dto.mapper;

import java.util.HashSet;
import org.springframework.stereotype.Component;
import ru.dankoy.hw19.core.domain.Work;
import ru.dankoy.hw19.core.dto.WorkDTO;


@Component
public class BookMapperImpl implements BookMapper {

  @Override
  public WorkDTO toDTOWithoutCommentaries(Work work) {

    return WorkDTO.builder()
        .id(work.getId())
        .name(work.getName())
        .description(work.getDescription())
        .dateCreated(work.getDateCreated())
        .dateModified(work.getDateModified())
        .genres(work.getGenres())
        .authors(work.getAuthors())
        .commentaries(new HashSet<>())
        .build();

  }

  @Override
  public WorkDTO toSimpleDTO(Work work) {

    return WorkDTO.builder()
        .id(work.getId())
        .name(work.getName())
        .description(work.getDescription())
        .dateCreated(work.getDateCreated())
        .dateModified(work.getDateModified())
        .genres(new HashSet<>())
        .authors(new HashSet<>())
        .commentaries(new HashSet<>())
        .build();

  }

  @Override
  public WorkDTO toDTOWithCommentaries(Work work) {

    return WorkDTO.builder()
        .id(work.getId())
        .name(work.getName())
        .description(work.getDescription())
        .genres(work.getGenres())
        .authors(work.getAuthors())
        .editions(work.getEditions())
        .dateCreated(work.getDateCreated())
        .dateModified(work.getDateModified())
        .build();

  }


  @Override
  public Work toBook(WorkDTO dto) {

    return new Work(
        dto.getId(),
        dto.getName(),
        dto.getDescription(),
        dto.getAuthors(),
        dto.getGenres(),
        dto.getEditions(),
        dto.getDateCreated(),
        dto.getDateModified()
    );

  }

}
