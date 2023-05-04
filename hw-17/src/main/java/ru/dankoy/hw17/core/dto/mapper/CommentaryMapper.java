package ru.dankoy.hw17.core.dto.mapper;

import ru.dankoy.hw17.core.domain.Commentary;
import ru.dankoy.hw17.core.dto.CommentaryDTO;

public interface CommentaryMapper {

  Commentary toCommentary(CommentaryDTO dto);

  CommentaryDTO toDTO(Commentary commentary);

}
