package ru.dankoy.hw10.core.dto.mapper;

import ru.dankoy.hw10.core.domain.Commentary;
import ru.dankoy.hw10.core.dto.CommentaryDTO;

public interface CommentaryMapper {

  Commentary toCommentary(CommentaryDTO dto);

  CommentaryDTO toDTO(Commentary commentary);

}
