package ru.dankoy.hw19.core.dto.work;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.dankoy.hw19.core.domain.Author;
import ru.dankoy.hw19.core.domain.Genre;
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

}
