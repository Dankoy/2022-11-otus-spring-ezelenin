package ru.dankoy.hw5.core.dao.book.mergemanytomanybycode.manytomany;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class BookGenreRelation {

  private final long bookId;
  private final long genreId;

}
