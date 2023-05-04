package ru.dankoy.hw12.core.service.commentary;

import ru.dankoy.hw12.core.dto.commentary.CommentaryDTO;

public interface CommentaryDtoService {

  CommentaryDTO insertOrUpdate(CommentaryDTO commentary);

  void deleteById(long id);


}
