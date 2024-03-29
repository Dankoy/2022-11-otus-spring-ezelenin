package ru.dankoy.hw13.core.service.book;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dankoy.hw13.core.dto.book.BookDTO;
import ru.dankoy.hw13.core.dto.book.mapper.BookMapper;
import ru.dankoy.hw13.core.exceptions.LibraryElement;
import ru.dankoy.hw13.core.exceptions.EntityNotFoundException;


@Service
@RequiredArgsConstructor
public class BookDtoServiceImpl implements BookDtoService {

  private final BookService bookService;

  private final BookMapper bookMapper;

  @Override
  public List<BookDTO> getAllWithAuthorsAndGenres() {
    return bookService.getAll().stream()
        .map(bookMapper::toDTOWithoutCommentaries)
        .collect(Collectors.toList());
  }


  @Transactional
  @Override
  public Optional<BookDTO> getById(long id) {
    var optional = bookService.getById(id);
    var found = optional.orElseThrow(() -> new EntityNotFoundException(id, LibraryElement.BOOK));
    return Optional.of(bookMapper.toDTOWithCommentaries(found));
  }

  @Override
  public BookDTO insertOrUpdate(BookDTO bookDTO) {

    var book = bookService.insertOrUpdate(bookMapper.toBookWithoutCommentaries(bookDTO));
    return bookMapper.toDTOWithoutCommentaries(book);

  }

  @Override
  public void deleteById(long id) {
    bookService.deleteById(id);
  }

  @Override
  public BookDTO update(BookDTO bookDTO) {
    var book = bookService.update(bookMapper.toBookWithoutCommentaries(bookDTO));
    return bookMapper.toDTOWithCommentaries(book);
  }
}
