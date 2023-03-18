package ru.dankoy.hw13.core.controller;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.dankoy.hw13.config.security.SecurityConfiguration;
import ru.dankoy.hw13.core.domain.Author;
import ru.dankoy.hw13.core.domain.Book;
import ru.dankoy.hw13.core.domain.Commentary;
import ru.dankoy.hw13.core.domain.Genre;
import ru.dankoy.hw13.core.dto.book.BookDTO;
import ru.dankoy.hw13.core.dto.book.mapper.BookMapperImpl;
import ru.dankoy.hw13.core.service.author.AuthorService;
import ru.dankoy.hw13.core.service.book.BookDtoService;
import ru.dankoy.hw13.core.service.genre.GenreService;


@DisplayName("BookController test ")
@WebMvcTest(controllers = {BookController.class})
@Import({SecurityConfiguration.class,
    BookMapperImpl.class}) // надо обязательно имортировать конфиг, иначе фильтров не будет
class BookControllerTest {

  @Autowired
  private MockMvc mvc;
  @Autowired
  private BookMapperImpl bookMapper;

  @MockBean
  private BookDtoService bookDtoService;

  @MockBean
  private AuthorService authorService;

  @MockBean
  private GenreService genreService;


  @DisplayName("should correctly print all books")
  @WithMockUser(
      username = "user",
      roles = {"ADMIN", "OPERATOR", "READER"}
  )
  @Test
  void shouldReturnCorrectBooksList() throws Exception {
    List<Book> books = makeCorrectAllBooksList();
    List<BookDTO> bookDTOS = books.stream()
        .map(bookMapper::toDTOWithoutCommentaries)
        .collect(Collectors.toList());

    given(bookDtoService.getAllWithAuthorsAndGenres()).willReturn(bookDTOS);

    mvc.perform(get("/books"))
        .andExpect(status().isOk())
        .andExpect(view().name("books"));

    Mockito.verify(bookDtoService, times(1)).getAllWithAuthorsAndGenres();

  }

  @DisplayName("should throw 403 when get all books")
  @WithMockUser(
      username = "user",
      roles = {"WEIRD"}
  )
  @Test
  void shouldThrow403WhenGetBooksList() throws Exception {
    List<Book> books = makeCorrectAllBooksList();
    List<BookDTO> bookDTOS = books.stream()
        .map(bookMapper::toDTOWithoutCommentaries)
        .collect(Collectors.toList());

    given(bookDtoService.getAllWithAuthorsAndGenres()).willReturn(bookDTOS);

    mvc.perform(get("/books"))
        .andExpect(status().isForbidden());

    Mockito.verify(bookDtoService, times(0)).getAllWithAuthorsAndGenres();

  }

  @DisplayName("should correctly return book view")
  @WithMockUser(
      roles = {"READER", "ADMIN", "OPERATOR"}
  )
  @Test
  void shouldGetBookById() throws Exception {

    var bookId = 1L;

    var books = makeCorrectAllBooksList();
    var book = books.stream()
        .filter(b -> b.getId() == bookId)
        .findFirst().get();
    var dto = Optional.of(bookMapper.toDTOWithCommentaries(book));

    given(bookDtoService.getById(bookId)).willReturn(dto);

    mvc.perform(get("/book?id=" + bookId))
        .andExpect(status().isOk())
        .andExpect(view().name("book"));

    Mockito.verify(bookDtoService, times(1)).getById(bookId);

  }


  @DisplayName("should correctly return create book view")
  @WithMockUser(
      username = "none",
      roles = {"ADMIN", "OPERATOR"}
  )
  @Test
  void shouldCreateBook() throws Exception {

    var bookId = 1L;

    var books = makeCorrectAllBooksList();
    var book = books.stream()
        .filter(b -> b.getId() == bookId)
        .findFirst().get();
    var dto = Optional.of(bookMapper.toDTOWithCommentaries(book));

    given(bookDtoService.getById(bookId)).willReturn(dto);

    mvc.perform(get("/book/create"))
        .andExpect(status().isOk())
        .andExpect(view().name("book_create"));

    Mockito.verify(authorService, times(1)).getAll();
    Mockito.verify(genreService, times(1)).getAll();

  }

  @DisplayName("should throw 403 create book view")
  @WithMockUser(
      roles = {"READER"}
  )
  @Test
  void shouldThrow403ForReaderWhenCreateBook() throws Exception {

    var bookId = 1L;

    var books = makeCorrectAllBooksList();
    var book = books.stream()
        .filter(b -> b.getId() == bookId)
        .findFirst().get();
    var dto = Optional.of(bookMapper.toDTOWithCommentaries(book));

    given(bookDtoService.getById(bookId)).willReturn(dto);

    mvc.perform(get("/book/create"))
        .andExpect(status().isForbidden());

    Mockito.verify(authorService, times(0)).getAll();
    Mockito.verify(genreService, times(0)).getAll();

  }

  @DisplayName("should correctly redirect to book list after book creation")
  @WithMockUser(
      username = "user",
      roles = {"ADMIN", "OPERATOR"}
  )
  @Test
  void shouldReturnToBookListAfterCreation() throws Exception {

    var bookId = 1L;

    var books = makeCorrectAllBooksList();
    var book = books.stream()
        .filter(b -> b.getId() == bookId)
        .findFirst().get();
    var dto = Optional.of(bookMapper.toDTOWithCommentaries(book));

    mvc.perform(post("/book/create")
            .param("id", String.valueOf(book.getId()))
            .param("name", book.getName())
            .param("_authors", "1")
            .param("authors.id", "1")
            .param("authors.id", "2")
            .param("_genres", "1")
            .param("genres.id", "1")
            .param("genres.id", "2")
        )
        .andExpect(status().is3xxRedirection())
        .andExpect(view().name("redirect:/books"));

    Mockito.verify(bookDtoService, times(1)).insertOrUpdate(any());

  }

  @DisplayName("should throw 403 when redirect to book list after book creation")
  @WithMockUser(
      username = "user",
      roles = {"READER"}
  )
  @Test
  void shouldThrow403ForReaderWhenReturnToBookListAfterCreation() throws Exception {

    var bookId = 1L;

    var books = makeCorrectAllBooksList();
    var book = books.stream()
        .filter(b -> b.getId() == bookId)
        .findFirst().get();
    var dto = Optional.of(bookMapper.toDTOWithCommentaries(book));

    mvc.perform(post("/book/create")
            .param("id", String.valueOf(book.getId()))
            .param("name", book.getName())
            .param("_authors", "1")
            .param("authors.id", "1")
            .param("authors.id", "2")
            .param("_genres", "1")
            .param("genres.id", "1")
            .param("genres.id", "2")
        )
        .andExpect(status().isForbidden());

    Mockito.verify(bookDtoService, times(0)).insertOrUpdate(any());

  }


  @DisplayName("should correctly open update book view")
  @WithMockUser(
      roles = {"ADMIN", "OPERATOR"}
  )
  @Test
  void shouldCorrectlyOpenUpdateBookView() throws Exception {

    var bookId = 1L;

    var books = makeCorrectAllBooksList();
    var book = books.stream()
        .filter(b -> b.getId() == bookId)
        .findFirst().get();
    var dto = Optional.of(bookMapper.toDTOWithCommentaries(book));

    given(bookDtoService.getById(bookId)).willReturn(dto);

    mvc.perform(get("/book/edit?id=" + bookId))
        .andExpect(status().isOk())
        .andExpect(view().name("book_edit"));

    Mockito.verify(bookDtoService, times(1)).getById(bookId);
    Mockito.verify(authorService, times(1)).getAll();
    Mockito.verify(genreService, times(1)).getAll();

  }

  @DisplayName("should correctly update book and redirect to book list view")
  @WithMockUser(
      username = "user",
      authorities = {"ROLE_ADMIN", "ROLE_OPERATOR"}
  )
  @Test
  void shouldCorrectlyUpdateBookAndRedirectToBookListView() throws Exception {

    var bookId = 1L;

    var books = makeCorrectAllBooksList();
    var book = books.stream()
        .filter(b -> b.getId() == bookId)
        .findFirst().get();
    var dto = Optional.of(bookMapper.toDTOWithCommentaries(book));

    given(bookDtoService.update(any())).willReturn(dto.get());

    mvc.perform(post("/book/edit")
            .param("id", String.valueOf(book.getId()))
            .param("name", book.getName())
            .param("_authors", "1")
            .param("authors.id", "1")
            .param("authors.id", "2")
            .param("_genres", "1")
            .param("genres.id", "1")
            .param("genres.id", "2")
        )
        .andExpect(status().isOk())
        .andExpect(view().name("book"))
        .andExpect(model().attribute("book", dto.get()));

    Mockito.verify(bookDtoService, times(1)).update(any());

  }

  @DisplayName("should throw 403 when update book and redirect to book list view")
  @WithMockUser(
      username = "user",
      authorities = {"READER"}
  )
  @Test
  void shouldThrow403ForReaderWhenUpdateBookAndRedirectToBookListView() throws Exception {

    var bookId = 1L;

    var books = makeCorrectAllBooksList();
    var book = books.stream()
        .filter(b -> b.getId() == bookId)
        .findFirst().get();
    var dto = Optional.of(bookMapper.toDTOWithCommentaries(book));

    given(bookDtoService.update(any())).willReturn(dto.get());

    mvc.perform(post("/book/edit")
            .param("id", String.valueOf(book.getId()))
            .param("name", book.getName())
            .param("_authors", "1")
            .param("authors.id", "1")
            .param("authors.id", "2")
            .param("_genres", "1")
            .param("genres.id", "1")
            .param("genres.id", "2")
        )
        .andExpect(status().isForbidden());

    Mockito.verify(bookDtoService, times(0)).update(any());

  }

  @DisplayName("should correctly delete book and redirect to book list view")
  @WithMockUser(
      username = "user",
      roles = {"ADMIN"}
  )
  @Test
  void shouldCorrectlyDeleteBookAndRedirectToBookListView() throws Exception {

    var bookId = 1L;

    var books = makeCorrectAllBooksList();
    var book = books.stream()
        .filter(b -> b.getId() == bookId)
        .findFirst().get();
    var dto = Optional.of(bookMapper.toDTOWithCommentaries(book));

    given(bookDtoService.getById(bookId)).willReturn(dto);

    mvc.perform(get("/book/delete?id=" + bookId))
        .andExpect(status().is3xxRedirection())
        .andExpect(view().name("redirect:/books"));

    Mockito.verify(bookDtoService, times(1)).deleteById(bookId);

  }

  @DisplayName("should throw 403 when delete book and redirect to book list view")
  @WithMockUser(
      username = "user",
      roles = {"OPERATOR", "READER"}
  )
  @Test
  void shouldThrow403ForOperatorAndReaderWhenDeleteBookAndRedirectToBookListView()
      throws Exception {

    var bookId = 1L;

    var books = makeCorrectAllBooksList();
    var book = books.stream()
        .filter(b -> b.getId() == bookId)
        .findFirst().get();
    var dto = Optional.of(bookMapper.toDTOWithCommentaries(book));

    given(bookDtoService.getById(bookId)).willReturn(dto);

    mvc.perform(get("/book/delete?id=" + bookId))
        .andExpect(status().isForbidden());

    Mockito.verify(bookDtoService, times(0)).deleteById(bookId);

  }


  private List<Book> makeCorrectAllBooksList() {

    var book1 = new Book(1L, "book1",
        Set.of(new Author(1L, "author1"), new Author(2L, "author2")),
        Set.of(new Genre(1L, "genre1"), new Genre(2L, "genre2")),
        new HashSet<>());

    var book2 = new Book(2L, "book2",
        Set.of(new Author(2L, "author2"), new Author(3L, "author3")),
        Set.of(new Genre(2L, "genre2"), new Genre(3L, "genre3")),
        new HashSet<>());

    var book3 = new Book(3L, "book3",
        Set.of(new Author(1L, "author1"), new Author(3L, "author3")),
        Set.of(new Genre(1L, "genre1"), new Genre(3L, "genre3")),
        new HashSet<>());

    Set<Commentary> commentariesBook1 = Set.of(
        new Commentary(1L, "com1", book1),
        new Commentary(2L, "com2", book1),
        new Commentary(3L, "com3", book1));
    Set<Commentary> commentariesBook2 = Set.of(
        new Commentary(4L, "com4", book2),
        new Commentary(5L, "com5", book2),
        new Commentary(6L, "com6", book2));

    book1.setCommentaries(commentariesBook1);
    book2.setCommentaries(commentariesBook2);

    return List.of(
        book1,
        book2,
        book3
    );
  }


}
