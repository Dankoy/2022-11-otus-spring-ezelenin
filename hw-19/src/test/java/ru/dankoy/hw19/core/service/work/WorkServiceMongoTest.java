package ru.dankoy.hw19.core.service.work;


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
import ru.dankoy.hw19.core.domain.Author;
import ru.dankoy.hw19.core.domain.Genre;
import ru.dankoy.hw19.core.domain.Work;
import ru.dankoy.hw19.core.repository.work.WorkRepository;
import ru.dankoy.hw19.core.service.author.AuthorService;
import ru.dankoy.hw19.core.service.author.AuthorServiceMongo;
import ru.dankoy.hw19.core.service.commentary.CommentaryServiceMongo;


@Transactional(propagation = Propagation.NEVER)
@DisplayName("Test BookServiceMongo ")
@DataMongoTest
@Import({WorkServiceMongo.class, AuthorServiceMongo.class,
    CommentaryServiceMongo.class})
class WorkServiceMongoTest {

  @MockBean
  private WorkRepository workRepository;

  @MockBean
  private AuthorService authorService;

  @Autowired
  private WorkServiceMongo bookServiceMongo;


  @DisplayName("should return all books by genre name")
  @Test
  void shouldGetAllBooksByGenreNameTest() {

    String genreName = "genre1";
    var genre = new Genre(genreName);

    given(workRepository.findBookByGenres(genreName))
        .willReturn(makeCorrectAllBooksList());

    var books = bookServiceMongo.findAllByGenreName(genre);

    assertThat(books).isEqualTo(makeCorrectAllBooksList());
    Mockito.verify(workRepository, times(1)).findBookByGenres(genreName);
  }


  @DisplayName("should return all books")
  @Test
  void shouldGetAllBooksTest() {

    given(workRepository.findAll()).willReturn(makeCorrectAllBooksList());

    var books = bookServiceMongo.findAll();

    assertThat(books).isEqualTo(makeCorrectAllBooksList());
    Mockito.verify(workRepository, times(1)).findAll();
  }


  @DisplayName("should return correct count")
  @Test
  void shouldReturnCorrectCountTest() {

    given(workRepository.count()).willReturn(3L);

    var count = bookServiceMongo.count();

    assertThat(count).isEqualTo(makeCorrectAllBooksList().size());
    Mockito.verify(workRepository, times(1)).count();

  }

  @DisplayName("should return correct book by id")
  @Test
  void shouldReturnCorrectBookById() {

    var id = "1";

    var books = makeCorrectAllBooksList();
    var correctbook = getBookByIdFromList(books, id);

    given(workRepository.findById(id)).willReturn(Optional.ofNullable(correctbook));

    var book = bookServiceMongo.getById(id);

    assertThat(book).isPresent().get().isEqualTo(correctbook);
    Mockito.verify(workRepository, times(1)).findById(id);

  }

  @DisplayName("should correctly insert book in db")
  @Test
  void shouldCorrectlyInsertBook() {

    var authors = new HashSet<Author>();
    var genres = new HashSet<Genre>();

    var bookName = "book4";
    var descr = "descr";

    var id = "1L";
    var correctInsertedId = "4L";
    var author = new Author(id, "author1", null, null, null, null);
    var genre = new Genre("genre1");
    authors.add(author);
    genres.add(genre);

    var bookToInsert = new Work(null, bookName, descr, authors, genres, null, null, null);
    var insertedBook = new Work(correctInsertedId, bookName, descr, authors, genres, null, null,
        null);

    given(workRepository.saveAndCheckAuthors(bookToInsert)).willReturn(insertedBook);
    given(authorService.getById(id)).willReturn(Optional.of(author));

    var actual = bookServiceMongo.insertOrUpdate(bookToInsert);

    assertThat(actual).isEqualTo(insertedBook);
    Mockito.verify(workRepository, times(1)).saveAndCheckAuthors(bookToInsert);

  }

  @DisplayName("should correctly deleteByBookId book by id")
  @Test
  void shouldCorrectlyDeleteBookById() {

    var id = "1L";
    var toDelete = new Work(id, "name", "descr", new HashSet<>(), new HashSet<>(), null, null,
        null);

    given(workRepository.findById(id)).willReturn(Optional.of(toDelete));

    bookServiceMongo.deleteById(id);

    Mockito.verify(workRepository, times(1)).deleteByBookId(id);

  }


  @DisplayName("should update book by id")
  @Test
  void shouldCorrectlyUpdateBook() {

    var authors = new HashSet<Author>();
    var genres = new HashSet<Genre>();
    var id = "1L";
    var author = new Author(id, "author1", null, null, null, null);
    var genre = new Genre("genre1");
    authors.add(author);
    genres.add(genre);

    var bookToUpdate = new Work(id, "newName", "descr", authors, genres, null, null, null);

    given(workRepository.findById(id)).willReturn(Optional.of(bookToUpdate));
    given(authorService.getById(id)).willReturn(Optional.of(author));

    bookServiceMongo.insertOrUpdate(bookToUpdate);

    Mockito.verify(workRepository, times(1)).saveAndCheckAuthors(bookToUpdate);

  }

  private Work getBookByIdFromList(List<Work> works, String id) {

    var nonExistingId = "999999L";
    var bookOptional = works.stream().filter(book -> book.getId().equals(id))
        .findFirst();

    return bookOptional.orElse(
        new Work(nonExistingId, "nonexisting", "descr",
            new HashSet<>(),
            new HashSet<>()
            , null, null, null));

  }

  private List<Work> makeCorrectAllBooksList() {

    var book1 = new Work("1L", "book1", "descr",
        Set.of(new Author("1L", "author1", null, null, null, null),
            new Author("2L", "author2", null, null, null, null)),
        Set.of(new Genre("genre1"), new Genre("genre2"))
        , null, null, null);

    var book2 = new Work("2L", "book2", "descr",
        Set.of(new Author("2L", "author2", null, null, null, null),
            new Author("3L", "author3", null, null, null, null)),
        Set.of(new Genre("genre2"), new Genre("genre3"))
        , null, null, null);

    var book3 = new Work("3L", "book3", "descr",
        Set.of(new Author("1L", "author1", null, null, null, null),
            new Author("3L", "author3", null, null, null, null)),
        Set.of(new Genre("genre1"), new Genre("genre3"))
        , null, null, null);

    return List.of(
        book1,
        book2,
        book3
    );
  }


}
