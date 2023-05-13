package ru.dankoy.hw19.core.dto.mapper;

import java.util.HashSet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.dankoy.hw19.core.domain.Work;
import ru.dankoy.hw19.core.dto.WorkDTO;


@Component
@RequiredArgsConstructor
public class BookMapperImpl implements BookMapper {

  private final EditionMapper editionMapper;
  private final CommentaryMapper commentaryMapper;

  @Override
  public WorkDTO toDTOWithoutCommentaries(Work work) {

    return WorkDTO.builder()
        .id(work.getId())
        .name(work.getName())
        .description(work.getDescription())
        .dateCreated(work.getDateCreated())
        .dateModified(work.getDateModified())
        .editions(editionMapper.toSetDTO(work.getEditions()))
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
        .editions(editionMapper.toSetDTO(work.getEditions()))
        .dateCreated(work.getDateCreated())
        .dateModified(work.getDateModified())
        .build();

  }


  @Override
  public Work toBook(WorkDTO dto) {

//    Work.builder()
//        .id(dto.getId())
//        .name(dto.getName())
//        .description(dto.getDescription())
//        .genres(dto.getGenres())
//        .authors(dto.getAuthors())
//        .editions(editionMapper.fromSetDto(dto.getEditions()))
//        .
//        .build();

    return new Work(
        dto.getId(),
        dto.getName(),
        dto.getDescription(),
        dto.getAuthors(),
        dto.getGenres(),
        editionMapper.fromSetDto(dto.getEditions()),
        dto.getDateCreated(),
        dto.getDateModified(),
        dto.getCreateBy(),
        dto.getModifiedBy()
    );

  }

}
