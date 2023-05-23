package ru.dankoy.hw19.core.dto.work;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.dankoy.hw19.core.domain.Genre;
import ru.dankoy.hw19.core.domain.Work;
import ru.dankoy.hw19.core.dto.genre.GenreDTO;

@ToString
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_EMPTY)
public class WorkUpdateDTO {

  @NotEmpty
  private String id;

  @NotEmpty
  private String name;

  @NotEmpty
  private String description;

  @Valid
  @NotEmpty
  private Set<WorkCreateAuthorDTO> authors;

  @Valid
  @NotEmpty
  private Set<GenreDTO> genres;

  @Valid
  @NotNull
  private Set<WorkCreateEditionDTO> editions = new HashSet<>();

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
        dto.getEditions().stream().map(WorkCreateEditionDTO::fromDTO).collect(Collectors.toSet())
    );

  }

}
