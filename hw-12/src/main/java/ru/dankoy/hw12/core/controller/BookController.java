package ru.dankoy.hw12.core.controller;


import java.util.HashSet;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.dankoy.hw12.core.domain.Author;
import ru.dankoy.hw12.core.domain.Genre;
import ru.dankoy.hw12.core.dto.book.BookDTO;
import ru.dankoy.hw12.core.exceptions.Entity;
import ru.dankoy.hw12.core.exceptions.EntityNotFoundException;
import ru.dankoy.hw12.core.service.author.AuthorService;
import ru.dankoy.hw12.core.service.book.BookDtoService;
import ru.dankoy.hw12.core.service.genre.GenreService;


@RequiredArgsConstructor
@Controller
public class BookController {

  private final BookDtoService bookService;
  private final AuthorService authorService;
  private final GenreService genreService;


  @GetMapping("/books")
  public String getAll(Model model) {

    var books = bookService.getAllWithAuthorsAndGenres();

    model.addAttribute("books", books);
    return "books";

  }

  @GetMapping("/book")
  public String getById(@RequestParam("id") long id, Model model) {

    var optional = bookService.getById(id);
    var book = optional.orElseThrow(() -> new EntityNotFoundException(id, Entity.BOOK));

    model.addAttribute("book", book);
    return "book";

  }

  @GetMapping("/book/create")
  public String createForm(Model model) {

    var bookDto = new BookDTO(0, "", new HashSet<>(), new HashSet<>(), new HashSet<>());

    List<Author> authors = authorService.getAll();
    List<Genre> genres = genreService.getAll();

    model.addAttribute("authors", authors);
    model.addAttribute("genres", genres);
    model.addAttribute("book", bookDto);
    return "book_create";

  }

  @PostMapping("/book/create")
  public ModelAndView createForm(@Valid @ModelAttribute("book") BookDTO bookFormDTO) {

    bookService.insertOrUpdate(bookFormDTO);

    return new ModelAndView("redirect:/books");

  }

  @GetMapping("/book/edit")
  public String updateForm(@RequestParam("id") long id, Model model) {

    var optional = bookService.getById(id);
    var book = optional.orElseThrow(() -> new EntityNotFoundException(id, Entity.BOOK));

    List<Author> authors = authorService.getAll();
    List<Genre> genres = genreService.getAll();

    model.addAttribute("book", book);
    model.addAttribute("authors", authors);
    model.addAttribute("genres", genres);
    return "book_edit";
  }

  @PostMapping("/book/edit")
  public String update(@Valid @ModelAttribute("book") BookDTO book, Model model) {

    var updated = bookService.update(book);
    model.addAttribute("book", updated);
    return "book";
  }


  @GetMapping("/book/delete")
  public ModelAndView delete(@RequestParam("id") long id) {

    bookService.deleteById(id);

    return new ModelAndView("redirect:/books");
  }

}
