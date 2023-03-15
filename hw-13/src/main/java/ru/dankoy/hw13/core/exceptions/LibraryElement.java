package ru.dankoy.hw13.core.exceptions;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum LibraryElement {
  BOOK("Book"),
  AUTHOR("Author"),
  GENRE("Genre"),
  COMMENTARY("Commentary");

  private final String name;

}
