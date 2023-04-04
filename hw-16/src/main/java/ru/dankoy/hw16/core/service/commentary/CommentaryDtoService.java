package ru.dankoy.hw16.core.service.commentary;

import ru.dankoy.hw16.core.dto.commentary.CommentaryDTO;

public interface CommentaryDtoService {

  CommentaryDTO insertOrUpdate(CommentaryDTO commentary);

  void deleteById(long id);


}
