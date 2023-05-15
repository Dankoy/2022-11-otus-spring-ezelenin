package ru.dankoy.hw19.core.dto.commentary;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.dankoy.hw19.core.domain.Commentary;
import ru.dankoy.hw19.core.dto.user.UserDTO;
import ru.dankoy.hw19.core.dto.work.WorkCommentaryDTO;


@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentaryCreateDTO {

  private String id;

  private String text;

  @Setter
  private WorkCommentaryDTO work;

  public static Commentary toCommentary(CommentaryCreateDTO dto) {
    return new Commentary(dto.getId(),
        dto.getText(),
        null,
        WorkCommentaryDTO.fromDTO(dto.getWork()),
        null,
        null
    );
  }

  public static CommentaryCreateDTO toDTO(Commentary commentary) {
    return CommentaryCreateDTO.builder()
        .id(commentary.getId())
        .text(commentary.getText())
        .work(new WorkCommentaryDTO(commentary.getWork().getId()))
        .build();
  }

}
