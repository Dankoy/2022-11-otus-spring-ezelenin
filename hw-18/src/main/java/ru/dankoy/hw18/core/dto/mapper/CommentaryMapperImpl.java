package ru.dankoy.hw18.core.dto.mapper;

import org.springframework.stereotype.Component;
import ru.dankoy.hw18.core.domain.Commentary;
import ru.dankoy.hw18.core.dto.CommentaryDTO;


@Component
public class CommentaryMapperImpl implements CommentaryMapper {

  @Override
  public Commentary toCommentary(CommentaryDTO dto) {
    return new Commentary(dto.getId(), dto.getText(), dto.getBook());
  }

  @Override
  public CommentaryDTO toDTO(Commentary commentary) {
    return CommentaryDTO.builder()
        .id(commentary.getId())
        .text(commentary.getText())
        .book(commentary.getBook())
        .build();
  }
}
