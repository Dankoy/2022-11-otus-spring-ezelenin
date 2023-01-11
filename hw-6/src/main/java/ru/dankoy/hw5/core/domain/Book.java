package ru.dankoy.hw5.core.domain;

import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
public class Book {

  private final long id;

  private final String name;
  private final List<Author> authors;
  private final List<Genre> genres;

}
