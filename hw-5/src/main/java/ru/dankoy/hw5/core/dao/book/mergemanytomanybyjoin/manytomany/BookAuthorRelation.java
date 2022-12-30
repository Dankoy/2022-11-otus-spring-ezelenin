package ru.dankoy.hw5.core.dao.book.mergemanytomanybyjoin.manytomany;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class BookAuthorRelation {

  private final long bookId;
  private final long authorId;

}
