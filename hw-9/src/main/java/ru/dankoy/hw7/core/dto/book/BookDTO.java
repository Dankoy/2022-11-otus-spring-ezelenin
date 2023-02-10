package ru.dankoy.hw7.core.dto.book;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.dankoy.hw7.core.domain.Author;
import ru.dankoy.hw7.core.domain.Commentary;
import ru.dankoy.hw7.core.domain.Genre;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_EMPTY)
public class BookDTO {

  private long id;

  private String name;

  private Set<Author> authors;

  private Set<Genre> genres;

  private Set<Commentary> commentaries;


}
