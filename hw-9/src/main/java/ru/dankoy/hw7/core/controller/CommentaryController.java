package ru.dankoy.hw7.core.controller;


import java.util.HashSet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.dankoy.hw7.core.dto.book.BookDTO;
import ru.dankoy.hw7.core.dto.commentary.CommentaryDTO;
import ru.dankoy.hw7.core.service.commentary.CommentaryDtoService;


@RequiredArgsConstructor
@Controller
public class CommentaryController {

  private final CommentaryDtoService commentaryDtoService;


  @GetMapping("/commentary/create")
  public String createCommentary(@RequestParam("bookId") long bookId, Model model) {

    var book = new BookDTO(bookId, null, new HashSet<>(), new HashSet<>(), new HashSet<>());
    var dto = CommentaryDTO.builder()
        .book(book)
        .build();

    model.addAttribute("commentary", dto);
    return "commentary_create";

  }

  @PostMapping("/commentary/create")
  public ModelAndView createCommentary(@ModelAttribute CommentaryDTO commentaryDTO,
      @RequestParam("bookId") long bookId) {

    var book = new BookDTO(bookId, null, new HashSet<>(), new HashSet<>(), new HashSet<>());

    commentaryDTO.setBook(book);

    var inserted = commentaryDtoService.insertOrUpdate(commentaryDTO);

    return new ModelAndView("redirect:/book?id=" + inserted.getBook().getId());

  }


  @GetMapping("/commentary/delete")
  public ModelAndView delete(@RequestParam("id") long id, @RequestParam("id") long bookId) {

    commentaryDtoService.deleteById(id);

    return new ModelAndView("redirect:/book?id=" + bookId);

  }


}
