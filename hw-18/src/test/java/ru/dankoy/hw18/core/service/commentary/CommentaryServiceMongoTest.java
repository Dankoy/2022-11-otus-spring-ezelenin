package ru.dankoy.hw18.core.service.commentary;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.dankoy.hw18.core.domain.Book;
import ru.dankoy.hw18.core.domain.Commentary;
import ru.dankoy.hw18.core.exceptions.EntityNotFoundException;
import ru.dankoy.hw18.core.repository.commentary.CommentaryRepository;
import ru.dankoy.hw18.core.service.author.AuthorServiceMongo;
import ru.dankoy.hw18.core.service.book.BookService;
import ru.dankoy.hw18.core.service.book.BookServiceMongo;
import ru.dankoy.hw18.core.service.genre.GenreServiceMongo;


@Transactional(propagation = Propagation.NEVER)
@DisplayName("Tests for CommentaryServiceMongo ")
@DataMongoTest
@Import({CommentaryServiceMongo.class, BookServiceMongo.class, GenreServiceMongo.class,
    AuthorServiceMongo.class})
class CommentaryServiceMongoTest {


  @MockBean
  private BookService bookService;

  @MockBean
  private CommentaryRepository commentaryRepository;

  @Autowired
  private CommentaryService commentaryService;


  @DisplayName("should correctly return commentary by id")
  @Test
  void shouldCorrectlyReturnCommentaryById() {

    var id = "1L";

    var book = new Book(id, "name", new HashSet<>(), new HashSet<>());
    var found = new Commentary(id, "com", book);
    given(commentaryRepository.findById(id)).willReturn(Optional.of(found));

    var actual = commentaryService.getById(id);

    assertThat(actual).isPresent().get().isEqualTo(found);
    Mockito.verify(commentaryRepository, times(1)).findById(id);

  }


  @DisplayName("should correctly return empty commentary by id")
  @Test
  void shouldReturnEmptyCommentaryById() {

    var id = "1L";

    given(commentaryRepository.findById(id)).willReturn(Optional.empty());

    var actual = commentaryService.getById(id);

    assertThat(actual).isEmpty();
    Mockito.verify(commentaryRepository, times(1)).findById(id);

  }


  @DisplayName("should correctly return all commentaries by book id")
  @Test
  void shouldReturnAllCommentariesByBookId() {

    var id = "1L";

    given(commentaryRepository.findAllByBookId(id)).willReturn(
        new ArrayList<>(makeCorrectCommentaryList()));

    var actual = commentaryService.getAllByBookId(id);

    assertThat(actual).isEqualTo(new ArrayList<>(makeCorrectCommentaryList()));
    Mockito.verify(commentaryRepository, times(1)).findAllByBookId(id);

  }


  @DisplayName("should correctly deleteByBookId commentary by id")
  @Test
  void shouldCorrectlyDeleteCommentaryById() {

    var id = "1L";

    var book = new Book(id, "name", new HashSet<>(), new HashSet<>());
    var commentary = new Commentary(id, "com", book);
    given(bookService.getById(id)).willReturn(Optional.of(book));
    given(commentaryRepository.findById(id)).willReturn(Optional.of(commentary));

    commentaryService.deleteById(id);

    Mockito.verify(commentaryRepository, times(1)).findById(id);
    Mockito.verify(commentaryRepository, times(1)).delete(commentary);

  }


  @DisplayName("should throw exception when deleteByBookId commentary by id")
  @Test
  void shouldThrowExceptionWhenDeleteCommentaryById() {

    var id = "1L";

    var book = new Book(id, "name", new HashSet<>(), new HashSet<>());
    given(bookService.getById(id)).willReturn(Optional.of(book));
    given(commentaryRepository.findById(id)).willReturn(Optional.empty());

    assertThatThrownBy(() -> commentaryService.deleteById(id))
        .isInstanceOf(EntityNotFoundException.class);

    Mockito.verify(commentaryRepository, times(1)).findById(id);
    Mockito.verify(commentaryRepository, times(0)).delete(any());

  }


  @DisplayName("should correctly update commentary")
  @Test
  void shouldCorrectlyUpdateCommentary() {

    var id = "1L";

    var book = new Book(id, "name", new HashSet<>(), new HashSet<>());
    var commentary = new Commentary(id, "com", book);

    commentaryService.insertOrUpdate(commentary);

    Mockito.verify(commentaryRepository, times(1)).save(commentary);


  }


  private Set<Commentary> makeCorrectCommentaryList() {

    var book = new Book("1L", "name", new HashSet<>(), new HashSet<>());
    return Set.of(new Commentary("1L", "com1", book),
        new Commentary("2L", "com2", book),
        new Commentary("3L", "com3", book));

  }

}
