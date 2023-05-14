package ru.dankoy.hw19.core.dto.mappers;

import ru.dankoy.hw19.core.domain.Work;
import ru.dankoy.hw19.core.dto.work.WorkDTO;

public interface BookMapper {

  WorkDTO toDTOWithoutCommentaries(Work work);

  WorkDTO toSimpleDTO(Work work);

  WorkDTO toDTOWithCommentaries(Work work);

  Work toBook(WorkDTO dto);
}
