package ru.dankoy.hw19.core.dto.commentary;


import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.dankoy.hw19.core.domain.Commentary;
import ru.dankoy.hw19.core.dto.user.UserMetaDTO;
import ru.dankoy.hw19.core.dto.work.WorkCommentaryDTO;


@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentaryFullDTO {

  private String id;

  private String text;

  private WorkCommentaryDTO work;

  private UserMetaDTO createdByUser;

  private UserMetaDTO modifiedByUser;

  private LocalDateTime dateCreated;

  private LocalDateTime dateModified;

  public static Commentary toCommentary(CommentaryFullDTO dto) {
    return new Commentary(dto.getId(),
        dto.getText(),
        WorkCommentaryDTO.fromDTO(dto.getWork()));
  }

  public static CommentaryFullDTO toDTO(Commentary commentary) {
    return CommentaryFullDTO.builder()
        .id(commentary.getId())
        .text(commentary.getText())
        .createdByUser(UserMetaDTO.toDTO(commentary.getCreatedByUser()))
        .modifiedByUser(UserMetaDTO.toDTO(commentary.getModifiedByUser()))
        .work(new WorkCommentaryDTO(commentary.getWork().getId()))
        .dateCreated(commentary.getDateCreated())
        .dateModified(commentary.getDateModified())
        .build();
  }

}
