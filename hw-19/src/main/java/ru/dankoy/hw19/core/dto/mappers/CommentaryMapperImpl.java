package ru.dankoy.hw19.core.dto.mappers;

import org.springframework.stereotype.Component;
import ru.dankoy.hw19.core.domain.Commentary;
import ru.dankoy.hw19.core.dto.commentary.CommentaryCreateDTO;
import ru.dankoy.hw19.core.dto.commentary.CommentaryDTO;
import ru.dankoy.hw19.core.dto.user.UserDTO;
import ru.dankoy.hw19.core.dto.work.WorkCommentaryDTO;


@Component
public class CommentaryMapperImpl implements CommentaryMapper {

  @Override
  public Commentary toCommentary(CommentaryDTO dto) {
    return new Commentary(dto.getId(),
        dto.getText(),
        UserDTO.fromDTO(dto.getUser()),
        WorkCommentaryDTO.fromDTO(dto.getWork()),
        dto.getDateCreated(),
        dto.getDateModified());
  }

  @Override
  public Commentary toCommentary(CommentaryCreateDTO dto) {
    return new Commentary(dto.getId(),
        dto.getText(),
        null,
        WorkCommentaryDTO.fromDTO(dto.getWork()),
        null,
        null
    );
  }

  @Override
  public CommentaryDTO toDTO(Commentary commentary) {
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
