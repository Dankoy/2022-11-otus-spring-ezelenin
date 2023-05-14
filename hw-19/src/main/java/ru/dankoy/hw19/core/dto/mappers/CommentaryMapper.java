package ru.dankoy.hw19.core.dto.mappers;

import ru.dankoy.hw19.core.domain.Commentary;
import ru.dankoy.hw19.core.dto.commentary.CommentaryCreateDTO;
import ru.dankoy.hw19.core.dto.commentary.CommentaryDTO;

public interface CommentaryMapper {

  Commentary toCommentary(CommentaryDTO dto);

  Commentary toCommentary(CommentaryCreateDTO dto);

  CommentaryDTO toDTO(Commentary commentary);

}
