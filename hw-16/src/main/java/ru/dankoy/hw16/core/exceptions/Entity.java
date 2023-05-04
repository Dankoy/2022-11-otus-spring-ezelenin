package ru.dankoy.hw16.core.exceptions;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Entity {
  BOOK("Book"),
  AUTHOR("Author"),
  GENRE("Genre"),
  COMMENTARY("Commentary");

  private final String name;

}
