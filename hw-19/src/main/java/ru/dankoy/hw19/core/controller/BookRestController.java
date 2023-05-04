package ru.dankoy.hw19.core.controller;


import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.dankoy.hw19.core.dto.BookDTO;
import ru.dankoy.hw19.core.dto.mapper.BookMapper;
import ru.dankoy.hw19.core.exceptions.Entity;
import ru.dankoy.hw19.core.exceptions.EntityNotFoundException;
import ru.dankoy.hw19.core.service.book.BookService;


@RequiredArgsConstructor
@RestController
public class BookRestController {

  private final BookService bookService;
  private final BookMapper bookMapper;

  @GetMapping(value = "/api/v1/book",
      consumes = {"application/json"},
      produces = {"application/json"})
  public List<BookDTO> getAll() {

    var books = bookService.findAll();

    return books.stream()
        .map(bookMapper::toDTOWithoutCommentaries)
        .collect(Collectors.toList());

  }


  @DeleteMapping(value = "/api/v1/book/{id}",
      consumes = {"application/json"},
      produces = {"application/json"})
  @ResponseStatus(value = HttpStatus.ACCEPTED)
  public void delete(@PathVariable String id) {

    bookService.deleteById(id);

  }

  @GetMapping(value = "/api/v1/book/{id}",
      consumes = {"application/json"},
      produces = {"application/json"})
  public BookDTO getById(@PathVariable String id) {

    var book = bookService.getById(id)
        .orElseThrow(() -> new EntityNotFoundException(id, Entity.BOOK));

    return bookMapper.toDTOWithoutCommentaries(book);

  }


  @PutMapping(value = "/api/v1/book/{id}",
      consumes = {"application/json"},
      produces = {"application/json"})
  public BookDTO update(@PathVariable String id, @RequestBody BookDTO bookDTO) {

    var book = bookMapper.toBook(bookDTO);

    bookService.getById(id)
        .orElseThrow(() -> new EntityNotFoundException(id, Entity.BOOK));

    var updated = bookService.insertOrUpdate(book);

    return bookMapper.toDTOWithoutCommentaries(updated);

  }

  @PostMapping(value = "/api/v1/book",
      consumes = {"application/json"},
      produces = {"application/json"})
  public BookDTO create(@RequestBody BookDTO bookDTO) {

    var book = bookMapper.toBook(bookDTO);

    var created = bookService.insertOrUpdate(book);

    return bookMapper.toDTOWithoutCommentaries(created);

  }

}
