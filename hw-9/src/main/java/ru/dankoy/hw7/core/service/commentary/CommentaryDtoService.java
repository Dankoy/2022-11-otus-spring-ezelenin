package ru.dankoy.hw7.core.service.commentary;

import ru.dankoy.hw7.core.dto.commentary.CommentaryDTO;

public interface CommentaryDtoService {

  CommentaryDTO insertOrUpdate(CommentaryDTO commentary);

  void deleteById(long id);


}
