package ru.dankoy.hw5.core.dao.book.mergemanytomanybycode;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import ru.dankoy.hw5.core.dao.author.AuthorDaoJdbc;
import ru.dankoy.hw5.core.dao.genre.GenreDaoJdbc;
import ru.dankoy.hw5.core.domain.Author;
import ru.dankoy.hw5.core.domain.Book;
import ru.dankoy.hw5.core.domain.Genre;
import ru.dankoy.hw5.core.exceptions.BookDaoException;


@DisplayName("Test BookDaoJdbcMerge ")
@JdbcTest
@Import({BookDaoJdbcMerge.class, AuthorDaoJdbc.class, GenreDaoJdbc.class})
@TestPropertySource(properties="book.dao.join=false")
class BookDaoJdbcMergeTest {

  @Autowired
  private BookDaoJdbcMerge bookDaoJdbcMerge;


  @DisplayName("should return all books")
  @Test
  void shouldGetAllBooksTest() {
    var books = bookDaoJdbcMerge.getAll();

    assertThat(books).isEqualTo(makeCorrectAllBooksList());
  }


  @DisplayName("should return correct count")
  @Test
  void shouldReturnCorrectCountTest() {

    var count = bookDaoJdbcMerge.count();

    assertThat(count).isEqualTo(makeCorrectAllBooksList().size());

  }

  @DisplayName("should return correct book by id")
  @Test
  void shouldReturnCorrectBookById() {

    var id = 1;

    var books = makeCorrectAllBooksList();
    var correctbook = getBookByIdFromList(books, id);

    var book = bookDaoJdbcMerge.getById(id);

    assertThat(book).isEqualTo(correctbook);

  }

  @DisplayName("should throw bookDaoException for non existing book")
  @Test
  void shouldThrowBookDaoExceptionWhenGetById() {

    var id = 999;

    assertThatThrownBy(() -> bookDaoJdbcMerge.getById(id))
        .isInstanceOf(BookDaoException.class)
        .hasMessage(String.format("Book with id '%d' does not exist", id));

  }

  @DisplayName("should correctly insert book in db")
  @Test
  void shouldCorrectlyInsertBook() {

    var bookName = "newName";

    var id = 1L;
    var listOfIds = new long[]{id};
    var author = new Author(id, "author1");
    var genre = new Genre(id, "genre1");
    var bookToInsert = new Book(0L, bookName, List.of(author), List.of(genre));

    var insertedId = bookDaoJdbcMerge.insert(bookToInsert, listOfIds, listOfIds);

    var expected = new Book(insertedId, bookName, List.of(author), List.of(genre));

    var actual = bookDaoJdbcMerge.getById(insertedId);

    assertThat(actual).isEqualTo(expected);

  }

  @DisplayName("should correctly delete book by id")
  @Test
  void shouldCorrectlyDeleteBookById() {

    var id = 1L;

    assertThatCode(() -> bookDaoJdbcMerge.getById(id))
        .doesNotThrowAnyException();

    bookDaoJdbcMerge.deleteById(id);

    assertThatThrownBy(() -> bookDaoJdbcMerge.getById(id))
        .isInstanceOf(BookDaoException.class)
        .hasMessage(String.format("Book with id '%d' does not exist", id));

  }

  @DisplayName("should correctly delete book by id")
  @Test
  void shouldThrowExceptionWhenDeleteNonExistingBookById() {

    var id = 999L;

    assertThatThrownBy(() -> bookDaoJdbcMerge.deleteById(id))
        .isInstanceOf(BookDaoException.class)
        .hasMessage(String.format("Can't delete book. Book with id '%d' does not exist", id));

  }


  @DisplayName("should update book by id")
  @Test
  void shouldCorrectlyUpdateBook() {

    var id = 1L;
    var listOfIds = new long[]{id};
    var author = new Author(id, "author1");
    var genre = new Genre(id, "genre1");
    var bookToUpdate = new Book(id, "newName", new ArrayList<>(), new ArrayList<>());

    bookDaoJdbcMerge.update(bookToUpdate, listOfIds, listOfIds);

    var fromDb = bookDaoJdbcMerge.getById(id);

    var correctBook = new Book(id, "newName", Stream.of(author).collect(Collectors.toList()),
        Stream.of(genre).collect(Collectors.toList()));

    assertThat(fromDb).isEqualTo(correctBook);

  }


  private Book getBookByIdFromList(List<Book> books, long id) {

    var nonExistingId = 999999L;
    var bookOptional = books.stream().filter(book -> book.getId() == id)
        .findFirst();

    return bookOptional.orElse(
        new Book(nonExistingId, "nonexisting",
            new ArrayList<>(),
            new ArrayList<>()));

  }

  private List<Book> makeCorrectAllBooksList() {
    return List.of(
        new Book(1L, "book1",
            List.of(new Author(1L, "author1"), new Author(2L, "author2")),
            List.of(new Genre(1L, "genre1"), new Genre(2L, "genre2"))),
        new Book(2L, "book2",
            List.of(new Author(2L, "author2"), new Author(3L, "author3")),
            List.of(new Genre(2L, "genre2"), new Genre(3L, "genre3"))),
        new Book(3L, "book3",
            List.of(new Author(1L, "author1"), new Author(3L, "author3")),
            List.of(new Genre(1L, "genre1"), new Genre(3L, "genre3")))
    );
  }


}
