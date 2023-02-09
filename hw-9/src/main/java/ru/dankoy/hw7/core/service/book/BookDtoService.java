package ru.dankoy.hw7.core.service.book;

import java.util.List;
import java.util.Optional;
import ru.dankoy.hw7.core.dto.BookDTO;
import ru.dankoy.hw7.core.dto.BookFormDTO;

public interface BookDtoService {

  List<BookDTO> getAllWithAuthorsAndGenres();

  Optional<BookDTO> getById(long id);

  BookDTO insertOrUpdate(BookFormDTO bookDTO);

  void deleteById(long id);

  BookDTO update(BookFormDTO bookDTO);


}
