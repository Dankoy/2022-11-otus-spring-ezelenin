package ru.dankoy.hw13.core.dto.book;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.Set;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import ru.dankoy.hw13.core.domain.Author;
import ru.dankoy.hw13.core.domain.Commentary;
import ru.dankoy.hw13.core.domain.Genre;

@Getter
@Builder
@AllArgsConstructor
@JsonInclude(Include.NON_EMPTY)
public class BookDTO {

  private long id;


  @NotNull
  private String name;


  @NotNull
  private Set<Author> authors;


  @NotNull
  private Set<Genre> genres;


  private Set<Commentary> commentaries;


}
