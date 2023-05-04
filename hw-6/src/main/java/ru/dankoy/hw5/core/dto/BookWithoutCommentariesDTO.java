package ru.dankoy.hw5.core.dto;

import java.util.Set;
import lombok.Builder;
import lombok.Getter;
import ru.dankoy.hw5.core.domain.Author;
import ru.dankoy.hw5.core.domain.Genre;

@Getter
@Builder
public class BookWithoutCommentariesDTO {

  private final long id;

  private final String name;

  private Set<Author> authors;

  private Set<Genre> genres;


}
