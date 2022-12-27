package ru.dankoy.hw5.core.domain;

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
  private final Author author;
  private final Genre genre;

}
