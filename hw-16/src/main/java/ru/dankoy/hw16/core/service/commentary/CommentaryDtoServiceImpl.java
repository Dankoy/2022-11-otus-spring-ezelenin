package ru.dankoy.hw16.core.service.commentary;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import ru.dankoy.hw16.core.dto.commentary.CommentaryDTO;
import ru.dankoy.hw16.core.dto.commentary.mapper.CommentaryMapper;


@Service
@RequiredArgsConstructor
public class CommentaryDtoServiceImpl implements CommentaryDtoService {

  private final CommentaryMapper commentaryMapper;
  private final CommentaryService commentaryService;


  @Secured({"ADMIN", "OPERATOR"})
  @Override
  public CommentaryDTO insertOrUpdate(CommentaryDTO commentaryDto) {

    var commentary = commentaryService.insertOrUpdate(commentaryMapper.toCommentary(commentaryDto));
    return commentaryMapper.toDto(commentary);

  }

  @PreAuthorize("hasAnyRole({ T(ru.dankoy.hw16.config.security.Authority).ADMIN.name(),"
      + " T(ru.dankoy.hw16.config.security.Authority).OPERATOR.name(),"
      + " T(ru.dankoy.hw16.config.security.Authority).ADMIN.name()})")
  @Override
  public void deleteById(long id) {
    commentaryService.deleteById(id);
  }
}
