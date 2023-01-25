package ru.dankoy.hw8.core.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "book")
// фиксит рекурсивный вызов equals и hashcode комментария и книги
@Getter
public class Commentary {

  private long id;

  private String text;

  @JsonBackReference // решает проблему с рекурсивной сериализацией
  private Book book;

}
