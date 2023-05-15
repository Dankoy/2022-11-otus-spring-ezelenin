package ru.dankoy.hw19.core.dto.commentary;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.dankoy.hw19.core.dto.work.WorkCommentaryDTO;


@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentaryCreateDTO {

  private String id;

  private String text;

  @Setter
  private WorkCommentaryDTO work;

}
