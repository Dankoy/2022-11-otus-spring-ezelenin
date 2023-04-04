package ru.dankoy.hw10.core.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.dankoy.hw10.core.domain.Author;
import ru.dankoy.hw10.core.domain.Commentary;
import ru.dankoy.hw10.core.domain.Genre;

@ToString
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_EMPTY)
public class BookDTO {

  private String id;

  private String name;

  private Set<Author> authors;

  private Set<Genre> genres;

  @Setter
  private Set<Commentary> commentaries;


}
