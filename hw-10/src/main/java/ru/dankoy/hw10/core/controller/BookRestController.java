package ru.dankoy.hw10.core.controller;


import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dankoy.hw10.core.dto.BookDTO;
import ru.dankoy.hw10.core.dto.mapper.BookMapper;
import ru.dankoy.hw10.core.service.author.AuthorService;
import ru.dankoy.hw10.core.service.book.BookService;
import ru.dankoy.hw10.core.service.genre.GenreService;


@RequiredArgsConstructor
@RestController
public class BookRestController {

  private final BookService bookService;
  private final AuthorService authorService;
  private final GenreService genreService;

  private final BookMapper bookMapper;

  @GetMapping("/api/v1/book")
  public List<BookDTO> getAll() {

    var books = bookService.findAll();

    return books.stream()
        .map(bookMapper::toDTOWithoutCommentaries)
        .collect(Collectors.toList());

  }

}
