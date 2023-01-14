package ru.dankoy.hw5.core.service.book;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.dankoy.hw5.core.dao.author.AuthorDaoHibernate;
import ru.dankoy.hw5.core.dao.book.BookDao;
import ru.dankoy.hw5.core.dao.genre.GenreDaoHibernate;
import ru.dankoy.hw5.core.domain.Author;
import ru.dankoy.hw5.core.domain.Book;
import ru.dankoy.hw5.core.domain.Commentary;
import ru.dankoy.hw5.core.domain.Genre;
import ru.dankoy.hw5.core.exceptions.EntityNotFoundException;
import ru.dankoy.hw5.core.service.author.AuthorService;
import ru.dankoy.hw5.core.service.author.AuthorServiceHibernate;
import ru.dankoy.hw5.core.service.genre.GenreService;
import ru.dankoy.hw5.core.service.genre.GenreServiceHibernate;


@Transactional(propagation = Propagation.NEVER)
@DisplayName("Test BookServiceHibernate ")
@DataJpaTest
@Import({BookServiceHibernate.class, BookDao.class, GenreServiceHibernate.class,
    AuthorServiceHibernate.class, GenreDaoHibernate.class, AuthorDaoHibernate.class})
class BookServiceHibernateTest {

  @MockBean
  private BookDao bookDao;

  @MockBean
  private GenreService genreService;

  @MockBean
  private AuthorService authorService;

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

    given(bookDao.getById(id)).willReturn(Optional.ofNullable(correctbook));

    var book = bookServiceHibernate.getById(id);

    assertThat(book).isPresent().get().isEqualTo(correctbook);
    Mockito.verify(bookDao, times(1)).getById(id);

  }

  @DisplayName("should correctly insert book in db")
  @Test
  void shouldCorrectlyInsertBook() {

    var authors = new HashSet<Author>();
    var genres = new HashSet<Genre>();

    var bookName = "book4";

    var id = 1L;
    var correctInsertedId = 4L;
    var listOfIds = new long[]{id};
    var author = new Author(id, "author1");
    var genre = new Genre(id, "genre1");
    authors.add(author);
    genres.add(genre);

    var bookToInsert = new Book(0L, bookName, authors, genres, new HashSet<>());
    var insertedBook = new Book(correctInsertedId, bookName, authors, genres, new HashSet<>());

    given(bookDao.insertOrUpdate(bookToInsert)).willReturn(insertedBook);
    given(genreService.getById(id)).willReturn(Optional.of(genre));
    given(authorService.getById(id)).willReturn(Optional.of(author));

    var actual = bookServiceHibernate.insertOrUpdate(bookToInsert, listOfIds, listOfIds);

    assertThat(actual).isEqualTo(insertedBook);
    Mockito.verify(bookDao, times(1)).insertOrUpdate(bookToInsert);

  }

  @DisplayName("should correctly delete book by id")
  @Test
  void shouldCorrectlyDeleteBookById() {

    var id = 1L;
    var toDelete = new Book(id, "name", new HashSet<>(), new HashSet<>(), new HashSet<>());

    given(bookDao.getById(id)).willReturn(Optional.of(toDelete));

    bookServiceHibernate.deleteById(id);

    Mockito.verify(bookDao, times(1)).delete(toDelete);

  }

  @DisplayName("should correctly delete book by id")
  @Test
  void shouldThrowExceptionWhenDeleteNonExistingBookById() {

    var id = 999L;

    given(bookDao.getById(id)).willReturn(Optional.empty());

    assertThatThrownBy(() -> bookServiceHibernate.deleteById(id))
        .isInstanceOf(EntityNotFoundException.class);

    Mockito.verify(bookDao, times(0)).delete(any());

  }


  @DisplayName("should update book by id")
  @Test
  void shouldCorrectlyUpdateBook() {

    var authors = new HashSet<Author>();
    var genres = new HashSet<Genre>();
    var id = 1L;
    var listOfIds = new long[]{id};
    var author = new Author(id, "author1");
    var genre = new Genre(id, "genre1");
    authors.add(author);
    genres.add(genre);

    var bookToUpdate = new Book(id, "newName", authors, genres, new HashSet<>());

    given(bookDao.getById(id)).willReturn(Optional.of(bookToUpdate));

    bookServiceHibernate.update(bookToUpdate, listOfIds, listOfIds);

    Mockito.verify(bookDao, times(1)).update(bookToUpdate);

  }

  @DisplayName("should throw exception when updating non existing book")
  @Test
  void shouldThrowExceptionWhenUpdatingNonExistingBook() {

    var id = 1L;
    var listOfIds = new long[]{id};
    var author = new Author(id, "author1");
    var genre = new Genre(id, "genre1");
    var bookToUpdate = new Book(id, "newName", Set.of(author), Set.of(genre), new HashSet<>());

    given(bookDao.getById(id)).willReturn(Optional.empty());

    assertThatThrownBy(() -> bookServiceHibernate.update(bookToUpdate, listOfIds, listOfIds))
        .isInstanceOf(EntityNotFoundException.class);

    Mockito.verify(bookDao, times(1)).getById(id);
    Mockito.verify(bookDao, times(0)).update(any());

  }

  @DisplayName("should throw exception when updating book with non existing genre")
  @Test
  void shouldThrowExceptionWhenUpdatingNonExistingGenreInBook() {

    var id = 1L;
    var listOfIds = new long[]{id};
    var author = new Author(id, "author1");
    var genre = new Genre(id, "genre1");
    var bookToUpdate = new Book(id, "newName", Set.of(author), Set.of(genre), new HashSet<>());

    given(bookDao.getById(id)).willReturn(Optional.empty());
    given(genreService.getById(id)).willReturn(Optional.empty());

    assertThatThrownBy(() -> bookServiceHibernate.update(bookToUpdate, listOfIds, listOfIds))
        .isInstanceOf(EntityNotFoundException.class);

    Mockito.verify(bookDao, times(1)).getById(id);
    Mockito.verify(bookDao, times(0)).update(any());

  }


  private Book getBookByIdFromList(List<Book> books, long id) {

    var nonExistingId = 999999L;
    var bookOptional = books.stream().filter(book -> book.getId() == id)
        .findFirst();

    return bookOptional.orElse(
        new Book(nonExistingId, "nonexisting",
            new HashSet<>(),
            new HashSet<>(),
            new HashSet<>()));

  }

  private List<Book> makeCorrectAllBooksList() {
    return List.of(
        new Book(1L, "book1",
            Set.of(new Author(1L, "author1"), new Author(2L, "author2")),
            Set.of(new Genre(1L, "genre1"), new Genre(2L, "genre2")),
            Set.of(new Commentary(1L, 1L, "com1"), new Commentary(2L, 1L, "com2"),
                new Commentary(3L, 1L, "com3"))),
        new Book(2L, "book2",
            Set.of(new Author(2L, "author2"), new Author(3L, "author3")),
            Set.of(new Genre(2L, "genre2"), new Genre(3L, "genre3")),
            Set.of(new Commentary(4L, 2L, "com4"), new Commentary(5L, 2L, "com5"),
                new Commentary(6L, 2L, "com6"))),
        new Book(3L, "book3",
            Set.of(new Author(1L, "author1"), new Author(3L, "author3")),
            Set.of(new Genre(1L, "genre1"), new Genre(3L, "genre3")),
            new HashSet<>())
    );
  }


}
