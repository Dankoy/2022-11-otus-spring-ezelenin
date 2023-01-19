package ru.dankoy.hw7.core.dto;

import java.util.Set;
import lombok.Builder;
import lombok.Getter;
import ru.dankoy.hw7.core.domain.Author;
import ru.dankoy.hw7.core.domain.Genre;

@Getter
@Builder
public class BookWithoutCommentariesDTO {

  private final long id;

  private final String name;

  private Set<Author> authors;

  private Set<Genre> genres;


}
