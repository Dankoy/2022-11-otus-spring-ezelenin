package ru.dankoy.hw19.core.dto.commentary;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.dankoy.hw19.core.domain.Commentary;

@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentaryModifyDTO {

  private String id;

  private String text;


  public static Commentary toCommentary(CommentaryModifyDTO dto) {
    return new Commentary(dto.getId(),
        dto.getText(),
        null,
        null,
        null,
        null
    );
  }

  public static CommentaryModifyDTO toDTO(Commentary commentary) {
    return CommentaryModifyDTO.builder()
        .id(commentary.getId())
        .text(commentary.getText())
        .build();
  }
}
