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
import ru.dankoy.hw19.core.domain.Work;
import ru.dankoy.hw19.core.dto.author.AuthorFullDTO;
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
public class WorkFullDTO {

  private String id;

  private String name;

  private String description;

  private Set<AuthorFullDTO> authors;

  private Set<GenreDTO> genres;

  @Setter
  private Set<CommentaryFullDTO> commentaries;

  private Set<EditionFullDTO> editions = new HashSet<>();

  private LocalDateTime dateCreated;

  private LocalDateTime dateModified;

  private UserMetaDTO createBy;

  private UserMetaDTO modifiedBy;


  public static WorkFullDTO toDTOWithoutCommentaries(Work work) {

    return WorkFullDTO.builder()
        .id(work.getId())
        .name(work.getName())
        .description(work.getDescription())
        .dateCreated(work.getDateCreated())
        .dateModified(work.getDateModified())
        .createBy(UserMetaDTO.toDTO(work.getCreatedByUser()))
        .modifiedBy(UserMetaDTO.toDTO(work.getModifiedByUser()))
        .editions(
            work.getEditions().stream()
                .map(EditionFullDTO::toDTO)
                .collect(Collectors.toSet()))
        .genres(work.getGenres().stream()
            .map(g -> new GenreDTO(g.getName()))
            .collect(Collectors.toSet()))
        .authors(work.getAuthors().stream()
            .map(AuthorFullDTO::fromAuthor)
            .collect(Collectors.toSet()))
        .commentaries(new HashSet<>())
        .build();

  }

  public static WorkFullDTO toSimpleDTO(Work work) {

    return WorkFullDTO.builder()
        .id(work.getId())
        .name(work.getName())
        .description(work.getDescription())
        .dateCreated(work.getDateCreated())
        .dateModified(work.getDateModified())
        .createBy(UserMetaDTO.toDTO(work.getCreatedByUser()))
        .modifiedBy(UserMetaDTO.toDTO(work.getModifiedByUser()))
        .genres(new HashSet<>())
        .authors(new HashSet<>())
        .commentaries(new HashSet<>())
        .build();

  }

  public static WorkFullDTO toDTOWithCommentaries(Work work) {

    return WorkFullDTO.builder()
        .id(work.getId())
        .name(work.getName())
        .description(work.getDescription())
        .genres(work.getGenres().stream()
            .map(g -> new GenreDTO(g.getName()))
            .collect(Collectors.toSet()))
        .authors(work.getAuthors().stream()
            .map(AuthorFullDTO::fromAuthor)
            .collect(Collectors.toSet()))
        .editions(
            work.getEditions().stream().map(EditionFullDTO::toDTO).collect(Collectors.toSet()))
        .dateCreated(work.getDateCreated())
        .dateModified(work.getDateModified())
        .createBy(UserMetaDTO.toDTO(work.getCreatedByUser()))
        .modifiedBy(UserMetaDTO.toDTO(work.getModifiedByUser()))
        .build();

  }


  public static Work toWork(WorkFullDTO dto) {

    return new Work(
        dto.getId(),
        dto.getName(),
        dto.getDescription(),
        dto.getAuthors().stream()
            .map(AuthorFullDTO::fromDTO)
            .collect(Collectors.toSet()),
        dto.getGenres().stream()
            .map(GenreDTO::fromDTO)
            .collect(Collectors.toSet()),
        dto.getEditions().stream().map(EditionFullDTO::fromDTO).collect(Collectors.toSet())
    );

  }

}
