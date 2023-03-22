package ru.dankoy.hw7.core.service.book;

import java.util.List;
import java.util.Optional;
import ru.dankoy.hw7.core.dto.book.BookDTO;

public interface BookDtoService {

  List<BookDTO> getAllWithAuthorsAndGenres();

  Optional<BookDTO> getById(long id);

  BookDTO insertOrUpdate(BookDTO bookDTO);

  void deleteById(long id);

  BookDTO update(BookDTO bookDTO);


}
