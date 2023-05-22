package ru.dankoy.hw19.core.dto.commentary;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.dankoy.hw19.core.domain.Commentary;
import ru.dankoy.hw19.core.dto.work.WorkCommentaryDTO;


@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentaryCreateUpdateDTO {

  private String id;

  private String text;

  @Setter
  private WorkCommentaryDTO work;

  public static Commentary fromUpdateDTO(CommentaryCreateUpdateDTO dto) {
    return new Commentary(
        dto.getId(),
        dto.getText(),
        WorkCommentaryDTO.fromDTO(dto.getWork())
    );
  }

  public static Commentary fromCreateDTO(CommentaryCreateUpdateDTO dto) {
    return new Commentary(
        null,
        dto.getText(),
        WorkCommentaryDTO.fromDTO(dto.getWork())
    );
  }

  public static CommentaryCreateUpdateDTO toDTO(Commentary commentary) {
    return CommentaryCreateUpdateDTO.builder()
        .id(commentary.getId())
        .text(commentary.getText())
        .work(new WorkCommentaryDTO(commentary.getWork().getId()))
        .build();
  }

}
