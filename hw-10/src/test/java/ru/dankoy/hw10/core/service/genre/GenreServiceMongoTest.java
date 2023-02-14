package ru.dankoy.hw10.core.service.genre;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;

import java.util.HashSet;
import java.util.List;
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
import ru.dankoy.hw10.core.domain.Author;
import ru.dankoy.hw10.core.domain.Book;
import ru.dankoy.hw10.core.domain.Genre;
import ru.dankoy.hw10.core.service.book.BookService;


@Transactional(propagation = Propagation.NEVER)
@DisplayName("Test GenreServiceMongo ")
@DataMongoTest
@Import({GenreServiceMongo.class})
class GenreServiceMongoTest {

  @MockBean
  private BookService bookService;

  @Autowired
  private GenreServiceMongo genreServiceMongo;


  @DisplayName("should return all genres")
  @Test
  void shouldGetAllGenresTest() {

    given(bookService.findAll()).willReturn(makeCorrectAllBooksList());

    var genres = genreServiceMongo.getAllGenres();

    assertThat(genres).isEqualTo(makeCorrectAllGenresList());
    Mockito.verify(bookService, times(1)).findAll();
  }


  @DisplayName("should correctly update genres")
  @Test
  void shouldUpdateGenre() {

    var oldGenre = new Genre("genre1");
    var newGenre = new Genre("new");
    var booksReturnFromService = makeCorrectAllBooksList();
    var expectedBooks = makeCorrectAllBooksList();

    given(bookService.findAllByGenreName(oldGenre)).willReturn(booksReturnFromService);

    booksAfterUpdateGenres(expectedBooks, oldGenre, newGenre);

    genreServiceMongo.update(oldGenre, newGenre);

    Mockito.verify(bookService, times(1))
        .updateMultiple(expectedBooks);
    Mockito.verify(bookService, times(1))
        .findAllByGenreName(oldGenre);
    assertThat(expectedBooks).containsExactlyInAnyOrderElementsOf(booksReturnFromService);

  }

  @DisplayName("should correctly delete genres from books")
  @Test
  void shouldDeleteGenre() {

    var toDelete = new Genre("genre1");
    var booksReturnFromService = makeCorrectAllBooksList();
    var expectedBooks = makeCorrectAllBooksList();

    given(bookService.findAllByGenreName(toDelete)).willReturn(booksReturnFromService);

    booksAfterDeleteGenres(expectedBooks, toDelete);

    genreServiceMongo.delete(toDelete);

    Mockito.verify(bookService, times(1)).updateMultiple(expectedBooks);
    Mockito.verify(bookService, times(1)).findAllByGenreName(toDelete);
    assertThat(expectedBooks).containsExactlyInAnyOrderElementsOf(booksReturnFromService);

  }

  private void booksAfterUpdateGenres(List<Book> books, Genre oldGenre, Genre newGenre) {

    books.forEach(b -> {
      b.getGenres().remove(oldGenre);
      b.getGenres().add(newGenre);
    });

  }

  private void booksAfterDeleteGenres(List<Book> books, Genre toDelete) {

    books.forEach(b -> b.getGenres().remove(toDelete));

  }


  private Set<Genre> makeCorrectAllGenresList() {
    return Set.of(
        new Genre("genre1"),
        new Genre("genre2"),
        new Genre("genre3")
    );
  }


  private List<Book> makeCorrectAllBooksList() {

    Set<Genre> genreBook1 = new HashSet<>();
    genreBook1.add(new Genre("genre1"));
    genreBook1.add(new Genre("genre2"));

    Set<Genre> genreBook2 = new HashSet<>();
    genreBook2.add(new Genre("genre2"));
    genreBook2.add(new Genre("genre3"));

    Set<Genre> genreBook3 = new HashSet<>();
    genreBook3.add(new Genre("genre1"));
    genreBook3.add(new Genre("genre3"));

    var book1 = new Book("1L", "book1",
        Set.of(new Author("1L", "author1"), new Author("2L", "author2")),
        genreBook1);

    var book2 = new Book("2L", "book2",
        Set.of(new Author("2L", "author2"), new Author("3L", "author3")),
        genreBook2);

    var book3 = new Book("3L", "book3",
        Set.of(new Author("1L", "author1"), new Author("3L", "author3")),
        genreBook3);

    return List.of(
        book1,
        book2,
        book3
    );
  }


}
