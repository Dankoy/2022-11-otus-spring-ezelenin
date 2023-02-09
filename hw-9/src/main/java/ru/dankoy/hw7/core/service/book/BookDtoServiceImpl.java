package ru.dankoy.hw7.core.service.book;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dankoy.hw7.core.dto.BookDTO;
import ru.dankoy.hw7.core.dto.BookFormDTO;
import ru.dankoy.hw7.core.dto.mapper.BookMapper;
import ru.dankoy.hw7.core.exceptions.Entity;
import ru.dankoy.hw7.core.exceptions.EntityNotFoundException;


@Service
@RequiredArgsConstructor
public class BookDtoServiceImpl implements BookDtoService {

  private final BookService bookService;

  private final BookMapper bookMapper;

  @Override
  public List<BookDTO> getAllWithAuthorsAndGenres() {
    return bookService.getAllWithAuthorsAndGenres().stream()
        .map(bookMapper::toDTOWithoutCommentaries)
        .collect(Collectors.toList());
  }


  @Transactional
  @Override
  public Optional<BookDTO> getById(long id) {
    var optional = bookService.getById(id);
    var found = optional.orElseThrow(() -> new EntityNotFoundException(id, Entity.BOOK));
    return Optional.of(bookMapper.toDTOWithCommentaries(found));
  }

  @Override
  public BookDTO insertOrUpdate(BookFormDTO bookDTO) {

    var book = bookService.insertOrUpdate(bookMapper.toBook(bookDTO));
    return bookMapper.toDTOWithoutCommentaries(book);

  }

  @Override
  public void deleteById(long id) {
    bookService.deleteById(id);
  }

  @Override
  public BookDTO update(BookFormDTO bookDTO) {
    var book = bookService.update(bookMapper.toBook(bookDTO));
    return bookMapper.toDTOWithoutCommentaries(book);
  }
}
