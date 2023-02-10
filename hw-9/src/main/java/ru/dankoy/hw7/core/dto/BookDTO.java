package ru.dankoy.hw7.core.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import ru.dankoy.hw7.core.domain.Author;
import ru.dankoy.hw7.core.domain.Commentary;
import ru.dankoy.hw7.core.domain.Genre;

@Getter
@Builder
@AllArgsConstructor
@JsonInclude(Include.NON_EMPTY)
public class BookDTO {

  private final long id;

  private final String name;

  private Set<Author> authors;

  private Set<Genre> genres;

  private Set<Commentary> commentaries;


}
