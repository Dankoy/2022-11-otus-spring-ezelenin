package ru.dankoy.hw5.core.service.book;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import ru.dankoy.hw5.core.dao.book.BookDao;
import ru.dankoy.hw5.core.domain.Author;
import ru.dankoy.hw5.core.domain.Book;
import ru.dankoy.hw5.core.domain.Genre;
import ru.dankoy.hw5.core.exceptions.BookDaoException;


@DisplayName("Test BookServiceJdbc ")
@JdbcTest
@Import({BookServiceHibernate.class, BookDao.class})
class BookServiceHibernateTest {

  @MockBean
  private BookDao bookDao;

  @Autowired
  private BookServiceHibernate bookServiceHibernate;


  @DisplayName("should return all books")
  @Test
  void shouldGetAllBooksTest() {

    given(bookDao.getAll()).willReturn(makeCorrectAllBooksList());

    var books = bookServiceHibernate.getAll();

    assertThat(books).isEqualTo(makeCorrectAllBooksList());
    Mockito.verify(bookDao, times(1)).getAll();
  }


  @DisplayName("should return correct count")
  @Test
  void shouldReturnCorrectCountTest() {

    given(bookDao.count()).willReturn(3L);

    var count = bookServiceHibernate.count();

    assertThat(count).isEqualTo(makeCorrectAllBooksList().size());
    Mockito.verify(bookDao, times(1)).count();

  }

  @DisplayName("should return correct book by id")
  @Test
  void shouldReturnCorrectBookById() {

    var id = 1;

    var books = makeCorrectAllBooksList();
    var correctbook = getBookByIdFromList(books, id);

    given(bookDao.getById(id)).willReturn(correctbook);

    var book = bookServiceHibernate.getById(id);

    assertThat(book).isEqualTo(correctbook);
    Mockito.verify(bookDao, times(1)).getById(id);

  }

  @DisplayName("should throw bookServiceException for non existing book")
  @Test
  void shouldThrowBookServiceExceptionWhenGetById() {

    var id = 999;

    Mockito.doThrow(new BookDaoException(new Exception())).when(bookDao).getById(id);

    assertThatThrownBy(() -> bookServiceHibernate.getById(id))
        .isInstanceOf(BookDaoException.class);

  }

  @DisplayName("should correctly insert book in db")
  @Test
  void shouldCorrectlyInsertBook() {

    var bookName = "book4";

    var id = 1L;
    var listOfIds = new long[]{id};
    var author = new Author(id, "author1");
    var genre = new Genre(id, "genre1");
    var bookToInsert = new Book(0L, bookName, List.of(author), List.of(genre));
    var correctInsertedId = 4L;

    given(bookDao.insert(bookToInsert)).willReturn(
        correctInsertedId);

    var insertedId = bookServiceHibernate.insert(bookToInsert, listOfIds, listOfIds);

    assertThat(insertedId).isEqualTo(correctInsertedId);
    Mockito.verify(bookDao, times(1))
        .insert(bookToInsert);

  }

  @DisplayName("should correctly delete book by id")
  @Test
  void shouldCorrectlyDeleteBookById() {

    var id = 1L;

    bookServiceHibernate.deleteById(id);

    Mockito.verify(bookDao, times(1)).deleteById(id);

  }

  @DisplayName("should correctly delete book by id")
  @Test
  void shouldThrowExceptionWhenDeleteNonExistingBookById() {

    var id = 999L;

    Mockito.doThrow(new BookDaoException(new Exception())).when(bookDao).deleteById(id);

    assertThatThrownBy(() -> bookServiceHibernate.deleteById(id))
        .isInstanceOf(BookDaoException.class);

    Mockito.verify(bookDao, times(1)).deleteById(id);

  }


  @DisplayName("should update book by id")
  @Test
  void shouldCorrectlyUpdateBook() {

    var id = 1L;
    var listOfIds = new long[]{id};
    var author = new Author(id, "author1");
    var genre = new Genre(id, "genre1");
    var bookToUpdate = new Book(id, "newName", List.of(author), List.of(genre));

    bookServiceHibernate.update(bookToUpdate, listOfIds, listOfIds);

    Mockito.verify(bookDao, times(1))
        .update(bookToUpdate, listOfIds, listOfIds);

  }

  @DisplayName("should throw exception when updating non existing book")
  @Test
  void shouldThrowExceptionWhenUpdatingNonExistingBook() {

    var id = 1L;
    var listOfIds = new long[]{id};
    var author = new Author(id, "author1");
    var genre = new Genre(id, "genre1");
    var bookToUpdate = new Book(id, "newName", List.of(author), List.of(genre));

    Mockito.doThrow(new BookDaoException(new Exception())).when(bookDao).getById(id);

    assertThatThrownBy(() -> bookServiceHibernate.update(bookToUpdate, listOfIds, listOfIds))
        .isInstanceOf(BookDaoException.class);

    Mockito.verify(bookDao, times(1))
        .getById(id);
    Mockito.verify(bookDao, times(0))
        .update(bookToUpdate, listOfIds, listOfIds);

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
