package ru.dankoy.hw19.core.dto.mappers;

import org.springframework.stereotype.Component;
import ru.dankoy.hw19.core.domain.Commentary;
import ru.dankoy.hw19.core.dto.commentary.CommentaryDTO;


@Component
public class CommentaryMapperImpl implements CommentaryMapper {

  @Override
  public Commentary toCommentary(CommentaryDTO dto) {
    return new Commentary(dto.getId(),
        dto.getText(),
        dto.getUser(),
        dto.getWork(),
        dto.getDateCreated(),
        dto.getDateModified());
  }

  @Override
  public CommentaryDTO toDTO(Commentary commentary) {
    return CommentaryDTO.builder()
        .id(commentary.getId())
        .text(commentary.getText())
        .work(commentary.getWork())
        .dateCreated(commentary.getDateCreated())
        .dateModified(commentary.getDateModified())
        .user(commentary.getUser())
        .build();
  }
}
