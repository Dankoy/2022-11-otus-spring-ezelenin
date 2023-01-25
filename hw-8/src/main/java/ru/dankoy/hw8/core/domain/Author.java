package ru.dankoy.hw8.core.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
public class Author {

  private String id;
  private String name;

  public Author(String id) {
    this.id = id;
  }

}
