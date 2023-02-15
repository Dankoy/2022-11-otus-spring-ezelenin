package ru.dankoy.hw10.core.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.Set;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.dankoy.hw10.core.domain.Author;
import ru.dankoy.hw10.core.domain.Commentary;
import ru.dankoy.hw10.core.domain.Genre;

@Getter
@Builder
@JsonInclude(Include.NON_EMPTY)
public class BookDTO {

  private final String id;

  private final String name;

  private Set<Author> authors;

  private Set<Genre> genres;

  @Setter
  private Set<Commentary> commentaries;


}
