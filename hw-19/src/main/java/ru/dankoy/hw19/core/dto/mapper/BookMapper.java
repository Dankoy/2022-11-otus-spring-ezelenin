package ru.dankoy.hw19.core.dto.mapper;

import ru.dankoy.hw19.core.domain.Work;
import ru.dankoy.hw19.core.dto.WorkDTO;

public interface BookMapper {

  WorkDTO toDTOWithoutCommentaries(Work work);

  WorkDTO toSimpleDTO(Work work);

  WorkDTO toDTOWithCommentaries(Work work);

  Work toBook(WorkDTO dto);
}
