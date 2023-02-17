package ru.dankoy.hw10.core.controller;


import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.dankoy.hw10.core.dto.BookDTO;
import ru.dankoy.hw10.core.dto.mapper.BookMapper;
import ru.dankoy.hw10.core.exceptions.Entity;
import ru.dankoy.hw10.core.exceptions.EntityNotFoundException;
import ru.dankoy.hw10.core.service.book.BookService;
import ru.dankoy.hw10.core.service.commentary.CommentaryService;


@RequiredArgsConstructor
@RestController
public class BookRestController {

  private final BookService bookService;
  private final CommentaryService commentaryService;
  private final BookMapper bookMapper;

  @GetMapping("/api/v1/book")
  public List<BookDTO> getAll() {

    var books = bookService.findAll();

    return books.stream()
        .map(bookMapper::toDTOWithoutCommentaries)
        .collect(Collectors.toList());

  }


  @DeleteMapping("/api/v1/book/{id}")
  public void delete(@PathVariable String id) {

    bookService.deleteById(id);

  }

  @GetMapping("/api/v1/book/{id}")
  public BookDTO getById(@PathVariable String id) {

    var book = bookService.getById(id)
        .orElseThrow(() -> new EntityNotFoundException(id, Entity.BOOK));
    var commentaries = commentaryService.getAllByBookId(id);

    var dto = bookMapper.toDTOWithoutCommentaries(book);
    dto.setCommentaries(new HashSet<>(commentaries));

    return dto;

  }


  @PutMapping("/api/v1/book/{id}")
  public BookDTO update(@PathVariable String id, @RequestBody BookDTO bookDTO) {

    var book = bookMapper.toBook(bookDTO);

    bookService.getById(id)
        .orElseThrow(() -> new EntityNotFoundException(id, Entity.BOOK));

    var updated = bookService.insertOrUpdate(book);

    return bookMapper.toDTOWithoutCommentaries(updated);

  }

  @PostMapping("/api/v1/book")
  public BookDTO create(@RequestBody BookDTO bookDTO) {

    var book = bookMapper.toBook(bookDTO);

    var updated = bookService.insertOrUpdate(book);

    return bookMapper.toDTOWithoutCommentaries(updated);

  }

}
