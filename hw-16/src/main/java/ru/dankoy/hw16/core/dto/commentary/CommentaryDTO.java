package ru.dankoy.hw16.core.dto.commentary;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.dankoy.hw16.core.dto.book.BookDTO;

@Getter
@Builder
@AllArgsConstructor
@JsonInclude(Include.NON_EMPTY)
public class CommentaryDTO {

  private long id;

  private String text;

  @Setter
  private BookDTO book;


}
