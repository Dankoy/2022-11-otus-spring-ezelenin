package ru.dankoy.hw5.core.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EqualsAndHashCode
@Getter
public class Author {

  private final long id;
  private final String name; // лень возится с разбиением автора на ФИО в отдельных полях

}
