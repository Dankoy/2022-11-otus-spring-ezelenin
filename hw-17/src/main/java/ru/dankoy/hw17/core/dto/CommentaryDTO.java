package ru.dankoy.hw17.core.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.dankoy.hw17.core.domain.Book;


@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentaryDTO {

  private String id;

  private String text;

  private Book book;

}
