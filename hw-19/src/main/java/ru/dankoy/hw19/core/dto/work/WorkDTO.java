package ru.dankoy.hw19.core.dto.work;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.dankoy.hw19.core.domain.Author;
import ru.dankoy.hw19.core.domain.Genre;
import ru.dankoy.hw19.core.domain.Work;
import ru.dankoy.hw19.core.dto.commentary.CommentaryDTO;
import ru.dankoy.hw19.core.dto.edition.EditionDTO;

@ToString
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_EMPTY)
public class WorkDTO {

  private String id;

  private String name;

  private String description;

  private Set<Author> authors;

  private Set<Genre> genres;

  @Setter
  private Set<CommentaryDTO> commentaries;

  private Set<EditionDTO> editions = new HashSet<>();

  private LocalDateTime dateCreated;

  private LocalDateTime dateModified;

  private String createBy;

  private String modifiedBy;


  public static WorkDTO toDTOWithoutCommentaries(Work work) {

    return WorkDTO.builder()
        .id(work.getId())
        .name(work.getName())
        .description(work.getDescription())
        .dateCreated(work.getDateCreated())
        .dateModified(work.getDateModified())
        .createBy(work.getCreatedByUser())
        .modifiedBy(work.getModifiedByUser())
        .editions(work.getEditions().stream().map(EditionDTO::toDTO).collect(Collectors.toSet()))
        .genres(work.getGenres())
        .authors(work.getAuthors())
        .commentaries(new HashSet<>())
        .build();

  }

  public static WorkDTO toSimpleDTO(Work work) {

    return WorkDTO.builder()
        .id(work.getId())
        .name(work.getName())
        .description(work.getDescription())
        .dateCreated(work.getDateCreated())
        .dateModified(work.getDateModified())
        .createBy(work.getCreatedByUser())
        .modifiedBy(work.getModifiedByUser())
        .genres(new HashSet<>())
        .authors(new HashSet<>())
        .commentaries(new HashSet<>())
        .build();

  }

  public static WorkDTO toDTOWithCommentaries(Work work) {

    return WorkDTO.builder()
        .id(work.getId())
        .name(work.getName())
        .description(work.getDescription())
        .genres(work.getGenres())
        .authors(work.getAuthors())
        .editions(work.getEditions().stream().map(EditionDTO::toDTO).collect(Collectors.toSet()))
        .dateCreated(work.getDateCreated())
        .dateModified(work.getDateModified())
        .createBy(work.getCreatedByUser())
        .modifiedBy(work.getModifiedByUser())
        .build();

  }


  public static Work toWork(WorkDTO dto) {

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
        dto.getEditions().stream().map(EditionDTO::fromDTO).collect(Collectors.toSet()),
        dto.getDateCreated(),
        dto.getDateModified(),
        dto.getCreateBy(),
        dto.getModifiedBy()
    );

  }

}
