package ru.dankoy.hw19.core.controller;


import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.dankoy.hw19.core.domain.Author;
import ru.dankoy.hw19.core.domain.Genre;
import ru.dankoy.hw19.core.domain.Work;
import ru.dankoy.hw19.core.dto.WorkDTO;
import ru.dankoy.hw19.core.dto.mapper.BookMapper;
import ru.dankoy.hw19.core.exceptions.Entity;
import ru.dankoy.hw19.core.exceptions.EntityNotFoundException;
import ru.dankoy.hw19.core.service.work.WorkService;

@DisplayName("BookRestController test ")
@SpringBootTest
@AutoConfigureMockMvc
class WorkRestControllerTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private WorkService workService;

  @Autowired
  private BookMapper bookMapper;

  @Autowired
  private ObjectMapper mapper;


  @DisplayName("should correctly return all books")
  @Test
  void shouldCorrectlyReturnAllBooks() throws Exception {

    List<Work> correctWorks = makeCorrectAllBooksList();

    List<WorkDTO> workDTOS = correctWorks.stream()
        .map(bookMapper::toDTOWithoutCommentaries)
        .collect(Collectors.toList());

    given(workService.findAll()).willReturn(correctWorks);

    mvc.perform(get("/api/v1/book")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().json(mapper.writeValueAsString(workDTOS)));

    Mockito.verify(workService, times(1)).findAll();

  }


  @DisplayName("should correctly return book by id")
  @Test
  void shouldGetBookById() throws Exception {

    List<Work> correctWorks = makeCorrectAllBooksList();
    var book = correctWorks.get(0);
    var bookId = book.getId();

    var bookDTO = bookMapper.toDTOWithoutCommentaries(book);

    given(workService.getById(bookId)).willReturn(Optional.of(book));

    mvc.perform(get("/api/v1/book/" + bookId)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().json(mapper.writeValueAsString(bookDTO)));

    Mockito.verify(workService, times(1)).getById(bookId);

  }


  @DisplayName("should throw EntityNotFoundException")
  @Test
  void shouldThrowEntityNotFoundExceptionWhenGetBookById() {

    List<Work> correctWorks = makeCorrectAllBooksList();
    var book = correctWorks.get(0);
    var bookId = book.getId();

    given(workService.getById(bookId)).willReturn(Optional.empty());

    assertThatThrownBy(() -> mvc.perform(get("/api/v1/book/" + bookId)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().is5xxServerError())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(
            result -> assertTrue(
                result.getResolvedException() instanceof EntityNotFoundException))
        .andExpect(result -> assertEquals("Entity Book has not been found with id - 1L",
            Objects.requireNonNull(result.getResolvedException()).getMessage()))).hasCause(
        new EntityNotFoundException(bookId, Entity.BOOK));

    Mockito.verify(workService, times(1)).getById(bookId);

  }

  @DisplayName("should delete book")
  @Test
  void shouldCorrectlyResponseWhenDeleteBookById() throws Exception {

    List<Work> correctWorks = makeCorrectAllBooksList();
    var book = correctWorks.get(0);
    var bookId = book.getId();

    mvc.perform(delete("/api/v1/book/" + bookId)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isAccepted())
        .andExpect(content().string(""));

    Mockito.verify(workService, times(1)).deleteById(bookId);

  }

  @DisplayName("should update book")
  @Test
  void shouldCorrectlyUpdateBookById() throws Exception {

    List<Work> correctWorks = makeCorrectAllBooksList();
    var book = correctWorks.get(0);
    var bookId = book.getId();

    given(workService.insertOrUpdate(book)).willReturn(book);
    given(workService.getById(bookId)).willReturn(Optional.of(book));

    mvc.perform(put("/api/v1/book/" + bookId)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(book)))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().string(mapper.writeValueAsString(book)));

    Mockito.verify(workService, times(1)).insertOrUpdate(book);

  }

  @DisplayName("should throw exception when update book")
  @Test
  void shouldThrowExceptionWhenUpdatingNotExistingBook() {

    List<Work> correctWorks = makeCorrectAllBooksList();
    var book = correctWorks.get(0);
    var bookId = book.getId();

    var dto = bookMapper.toDTOWithoutCommentaries(book);

    given(workService.insertOrUpdate(book)).willReturn(book);
    given(workService.getById(bookId)).willReturn(Optional.empty());

    assertThatThrownBy(() -> mvc.perform(put("/api/v1/book/" + bookId)
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(dto))
            .param("id", bookId)
            .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isNotFound())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(
            result -> assertTrue(
                result.getResolvedException() instanceof EntityNotFoundException))
        .andExpect(result -> assertEquals("Entity Book has not been found with id - 1L",
            Objects.requireNonNull(result.getResolvedException()).getMessage()))
    ).hasCause(new EntityNotFoundException(bookId, Entity.BOOK));

    Mockito.verify(workService, times(0)).insertOrUpdate(book);
    Mockito.verify(workService, times(1)).getById(bookId);

  }

  @DisplayName("should correctly create book")
  @Test
  void shouldCorrectlyCreateBook() throws Exception {

    List<Work> correctWorks = makeCorrectAllBooksList();
    var book = correctWorks.get(0);

    var dto = bookMapper.toDTOWithoutCommentaries(book);

    given(workService.insertOrUpdate(book)).willReturn(book);

    mvc.perform(post("/api/v1/book/")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(dto))
        )
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().string(mapper.writeValueAsString(dto)));

    Mockito.verify(workService, times(1)).insertOrUpdate(book);

  }


  private List<Work> makeCorrectAllBooksList() {

    var book1 = new Work("1L", "book1", "descr",
        Set.of(new Author("1L", "author1", null, null, null, null),
            new Author("2L", "author2", null, null, null, null)),
        Set.of(new Genre("genre1"), new Genre("genre2")),
        null,
        null,
        null);

    var book2 = new Work("2L", "book2", "descr",
        Set.of(new Author("2L", "author2", null, null, null, null),
            new Author("3L", "author3", null, null, null, null)),
        Set.of(new Genre("genre2"), new Genre("genre3")),
        null,
        null,
        null);

    var book3 = new Work("3L", "book3", "descr",
        Set.of(new Author("1L", "author1", null, null, null, null),
            new Author("3L", "author3", null, null, null, null)),
        Set.of(new Genre("genre1"), new Genre("genre3")),
        null,
        null,
        null);

    return List.of(
        book1,
        book2,
        book3
    );
  }

}
