package ru.dankoy.hw12.core.dto.commentary.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.dankoy.hw12.core.domain.Commentary;
import ru.dankoy.hw12.core.dto.book.mapper.BookMapper;
import ru.dankoy.hw12.core.dto.commentary.CommentaryDTO;


@RequiredArgsConstructor
@Component
public class CommentaryMapperImpl implements CommentaryMapper {

  private final BookMapper bookMapper;

  @Override
  public CommentaryDTO toDto(Commentary commentary) {
    return CommentaryDTO.builder()
        .id(commentary.getId())
        .text(commentary.getText())
        .book(bookMapper.toDTOWithCommentaries(commentary.getBook()))
        .build();
  }

  @Override
  public Commentary toCommentary(CommentaryDTO dto) {
    return new Commentary(
        dto.getId(),
        dto.getText(),
        bookMapper.toBook(dto.getBook())
    );
  }
}
