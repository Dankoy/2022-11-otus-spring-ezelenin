package ru.dankoy.hw19.core.dto.commentary;


import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.dankoy.hw19.core.dto.user.UserDTO;
import ru.dankoy.hw19.core.dto.work.WorkCommentaryDTO;


@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentaryDTO {

  private String id;

  private String text;

  private WorkCommentaryDTO work;

  private UserDTO user;

  private LocalDateTime dateCreated;

  private LocalDateTime dateModified;

}
