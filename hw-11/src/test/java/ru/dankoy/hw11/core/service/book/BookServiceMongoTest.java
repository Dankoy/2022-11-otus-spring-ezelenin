package ru.dankoy.hw11.core.service.book;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
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
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.dankoy.hw11.core.domain.Author;
import ru.dankoy.hw11.core.domain.Book;
import ru.dankoy.hw11.core.domain.Genre;
import ru.dankoy.hw11.core.repository.book.BookRepository;
import ru.dankoy.hw11.core.service.author.AuthorService;
import ru.dankoy.hw11.core.service.author.AuthorServiceMongo;
import ru.dankoy.hw11.core.service.commentary.CommentaryServiceMongo;


@Transactional(propagation = Propagation.NEVER)
@DisplayName("Test BookServiceMongo ")
@DataMongoTest
@Import({BookServiceMongo.class, AuthorServiceMongo.class,
    CommentaryServiceMongo.class})
class BookServiceMongoTest {

  @MockBean
  private BookRepository bookRepository;

  @MockBean
  private AuthorService authorService;

  @Autowired
  private BookServiceMongo bookServiceMongo;


  @DisplayName("should return all books by genre name")
  @Test
  void shouldGetAllBooksByGenreNameTest() {

    String genreName = "genre1";
    var genre = new Genre(genreName);

    given(bookRepository.findBookByGenres(genreName))
        .willReturn(makeCorrectAllBooksList());

    var books = bookServiceMongo.findAllByGenreName(genre);

    assertThat(books).isEqualTo(makeCorrectAllBooksList());
    Mockito.verify(bookRepository, times(1)).findBookByGenres(genreName);
  }


  @DisplayName("should return all books")
  @Test
  void shouldGetAllBooksTest() {

    given(bookRepository.findAll()).willReturn(makeCorrectAllBooksList());

    var books = bookServiceMongo.findAll();

    assertThat(books).isEqualTo(makeCorrectAllBooksList());
    Mockito.verify(bookRepository, times(1)).findAll();
  }


  @DisplayName("should return correct count")
  @Test
  void shouldReturnCorrectCountTest() {

    given(bookRepository.count()).willReturn(3L);

    var count = bookServiceMongo.count();

    assertThat(count).isEqualTo(makeCorrectAllBooksList().size());
    Mockito.verify(bookRepository, times(1)).count();

  }

  @DisplayName("should return correct book by id")
  @Test
  void shouldReturnCorrectBookById() {

    var id = "1";

    var books = makeCorrectAllBooksList();
    var correctbook = getBookByIdFromList(books, id);

    given(bookRepository.findById(id)).willReturn(Optional.ofNullable(correctbook));

    var book = bookServiceMongo.getById(id);

    assertThat(book).isPresent().get().isEqualTo(correctbook);
    Mockito.verify(bookRepository, times(1)).findById(id);

  }

  @DisplayName("should correctly insert book in db")
  @Test
  void shouldCorrectlyInsertBook() {

    var authors = new HashSet<Author>();
    var genres = new HashSet<Genre>();

    var bookName = "book4";

    var id = "1L";
    var correctInsertedId = "4L";
    var author = new Author(id, "author1");
    var genre = new Genre("genre1");
    authors.add(author);
    genres.add(genre);

    var bookToInsert = new Book(null, bookName, authors, genres);
    var insertedBook = new Book(correctInsertedId, bookName, authors, genres);

    given(bookRepository.saveAndCheckAuthors(bookToInsert)).willReturn(insertedBook);
    given(authorService.getById(id)).willReturn(Optional.of(author));

    var actual = bookServiceMongo.insertOrUpdate(bookToInsert);

    assertThat(actual).isEqualTo(insertedBook);
    Mockito.verify(bookRepository, times(1)).saveAndCheckAuthors(bookToInsert);

  }

  @DisplayName("should correctly deleteByBookId book by id")
  @Test
  void shouldCorrectlyDeleteBookById() {

    var id = "1L";
    var toDelete = new Book(id, "name", new HashSet<>(), new HashSet<>());

    given(bookRepository.findById(id)).willReturn(Optional.of(toDelete));

    bookServiceMongo.deleteById(id);

    Mockito.verify(bookRepository, times(1)).deleteByBookId(id);

  }


  @DisplayName("should update book by id")
  @Test
  void shouldCorrectlyUpdateBook() {

    var authors = new HashSet<Author>();
    var genres = new HashSet<Genre>();
    var id = "1L";
    var author = new Author(id, "author1");
    var genre = new Genre("genre1");
    authors.add(author);
    genres.add(genre);

    var bookToUpdate = new Book(id, "newName", authors, genres);

    given(bookRepository.findById(id)).willReturn(Optional.of(bookToUpdate));
    given(authorService.getById(id)).willReturn(Optional.of(author));

    bookServiceMongo.insertOrUpdate(bookToUpdate);

    Mockito.verify(bookRepository, times(1)).saveAndCheckAuthors(bookToUpdate);

  }

  private Book getBookByIdFromList(List<Book> books, String id) {

    var nonExistingId = "999999L";
    var bookOptional = books.stream().filter(book -> book.getId().equals(id))
        .findFirst();

    return bookOptional.orElse(
        new Book(nonExistingId, "nonexisting",
            new HashSet<>(),
            new HashSet<>()));

  }

  private List<Book> makeCorrectAllBooksList() {

    var book1 = new Book("1L", "book1",
        Set.of(new Author("1L", "author1"), new Author("2L", "author2")),
        Set.of(new Genre("genre1"), new Genre("genre2")));

    var book2 = new Book("2L", "book2",
        Set.of(new Author("2L", "author2"), new Author("3L", "author3")),
        Set.of(new Genre("genre2"), new Genre("genre3")));

    var book3 = new Book("3L", "book3",
        Set.of(new Author("1L", "author1"), new Author("3L", "author3")),
        Set.of(new Genre("genre1"), new Genre("genre3")));

    return List.of(
        book1,
        book2,
        book3
    );
  }


}
