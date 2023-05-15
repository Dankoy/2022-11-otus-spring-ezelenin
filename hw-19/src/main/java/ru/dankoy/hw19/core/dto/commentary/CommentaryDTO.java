package ru.dankoy.hw19.core.dto.commentary;


import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.dankoy.hw19.core.domain.Commentary;
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

  public static Commentary toCommentary(CommentaryDTO dto) {
    return new Commentary(dto.getId(),
        dto.getText(),
        UserDTO.fromDTO(dto.getUser()),
        WorkCommentaryDTO.fromDTO(dto.getWork()),
        dto.getDateCreated(),
        dto.getDateModified());
  }

  public static CommentaryDTO toDTO(Commentary commentary) {
    return CommentaryDTO.builder()
        .id(commentary.getId())
        .text(commentary.getText())
        .user(new UserDTO(commentary.getUser().getId()))
        .work(new WorkCommentaryDTO(commentary.getWork().getId()))
        .dateCreated(commentary.getDateCreated())
        .dateModified(commentary.getDateModified())
        .build();
  }

}
