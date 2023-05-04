package ru.dankoy.hw13.core.service.commentary;

import ru.dankoy.hw13.core.dto.commentary.CommentaryDTO;

public interface CommentaryDtoService {

  CommentaryDTO insertOrUpdate(CommentaryDTO commentary);

  void deleteById(long id);


}
