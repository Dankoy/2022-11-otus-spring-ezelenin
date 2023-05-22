package ru.dankoy.hw19.core.dto.work;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
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

@ToString
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_EMPTY)
public class WorkUpdateDTO {

  private String id;

  private String name;

  private String description;

  private Set<WorkCreateAuthorDTO> authors;

  private Set<GenreDTO> genres;

  @Setter
  private Set<CommentaryFullDTO> commentaries;

  private Set<EditionFullDTO> editions = new HashSet<>();

  public static Work toWork(WorkUpdateDTO dto) {

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
