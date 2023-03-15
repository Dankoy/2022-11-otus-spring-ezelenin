package ru.dankoy.hw13.core.service.commentary;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dankoy.hw13.core.dto.commentary.CommentaryDTO;
import ru.dankoy.hw13.core.dto.commentary.mapper.CommentaryMapper;


@Service
@RequiredArgsConstructor
public class CommentaryDtoServiceImpl implements CommentaryDtoService {

  private final CommentaryMapper commentaryMapper;
  private final CommentaryService commentaryService;

  @Override
  public CommentaryDTO insertOrUpdate(CommentaryDTO commentaryDto) {

    var commentary = commentaryService.insertOrUpdate(commentaryMapper.toCommentary(commentaryDto));
    return commentaryMapper.toDto(commentary);

  }

  @Override
  public void deleteById(long id) {
    commentaryService.deleteById(id);
  }
}
