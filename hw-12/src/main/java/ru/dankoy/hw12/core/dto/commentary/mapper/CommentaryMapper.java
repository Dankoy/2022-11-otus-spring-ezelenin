package ru.dankoy.hw12.core.dto.commentary.mapper;

import ru.dankoy.hw12.core.domain.Commentary;
import ru.dankoy.hw12.core.dto.commentary.CommentaryDTO;

public interface CommentaryMapper {

  CommentaryDTO toDto(Commentary book);

  Commentary toCommentary(CommentaryDTO dto);


}
