package ru.dankoy.hw19.core.dto;

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
import ru.dankoy.hw19.core.domain.Commentary;
import ru.dankoy.hw19.core.domain.Edition;
import ru.dankoy.hw19.core.domain.Genre;

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
  private Set<Commentary> commentaries;

  private Set<Edition> editions = new HashSet<>();

  private LocalDateTime dateWritten;

  private LocalDateTime dateCreated;

  private LocalDateTime dateModified;


}