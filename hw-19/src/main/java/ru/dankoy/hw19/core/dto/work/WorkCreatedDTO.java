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
import ru.dankoy.hw19.core.domain.Genre;
import ru.dankoy.hw19.core.domain.Work;
import ru.dankoy.hw19.core.dto.commentary.CommentaryFullDTO;
import ru.dankoy.hw19.core.dto.edition.EditionFullDTO;
import ru.dankoy.hw19.core.dto.genre.GenreDTO;
import ru.dankoy.hw19.core.dto.user.UserMetaDTO;

@ToString
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_EMPTY)
public class WorkCreatedDTO {

  private String id;

  private String name;

  private String description;

  private Set<WorkCreateAuthorDTO> authors;

  private Set<GenreDTO> genres;

  @Setter
  private Set<CommentaryFullDTO> commentaries;

  private Set<EditionFullDTO> editions = new HashSet<>();

  private LocalDateTime dateCreated;

  private LocalDateTime dateModified;

  private UserMetaDTO createBy;

  private UserMetaDTO modifiedBy;

  public static WorkCreatedDTO toDTOWithoutCommentaries(Work work) {

    return WorkCreatedDTO.builder()
        .id(work.getId())
        .name(work.getName())
        .description(work.getDescription())
        .dateCreated(work.getDateCreated())
        .dateModified(work.getDateModified())
        .createBy(UserMetaDTO.toDTO(work.getCreatedByUser()))
        .modifiedBy(UserMetaDTO.toDTO(work.getModifiedByUser()))
        .editions(
            work.getEditions().stream().map(EditionFullDTO::toDTO).collect(Collectors.toSet()))
        .genres(work.getGenres().stream()
            .map(g -> new GenreDTO(g.getName()))
            .collect(Collectors.toSet()))
        .authors(work.getAuthors().stream()
            .map(WorkCreateAuthorDTO::toDTO)
            .collect(Collectors.toSet()))
        .commentaries(new HashSet<>())
        .build();

  }

  public static Work toWork(WorkCreatedDTO dto) {

    return new Work(
        dto.getId(),
        dto.getName(),
        dto.getDescription(),
        dto.getAuthors().stream()
            .map(WorkCreateAuthorDTO::fromDTO)
            .collect(Collectors.toSet()),
        dto.getGenres().stream()
            .map(g -> new Genre(g.getName()))
            .collect(Collectors.toSet()),
        dto.getEditions().stream().map(EditionFullDTO::fromDTO).collect(Collectors.toSet())
    );

  }

}
