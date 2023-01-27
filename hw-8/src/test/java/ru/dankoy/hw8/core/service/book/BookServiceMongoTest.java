package ru.dankoy.hw8.core.service.book;


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
import ru.dankoy.hw8.core.domain.Author;
import ru.dankoy.hw8.core.domain.Book;
import ru.dankoy.hw8.core.domain.Commentary;
import ru.dankoy.hw8.core.domain.Genre;
import ru.dankoy.hw8.core.exceptions.EntityNotFoundException;
import ru.dankoy.hw8.core.repository.book.BookRepository;
import ru.dankoy.hw8.core.service.author.AuthorService;
import ru.dankoy.hw8.core.service.author.AuthorServiceMongo;
import ru.dankoy.hw8.core.service.genre.GenreService;
import ru.dankoy.hw8.core.service.genre.GenreServiceMongo;


@Transactional(propagation = Propagation.NEVER)
@DisplayName("Test BookServiceJpa ")
//@DataJpaTest
@Import({BookServiceMongo.class, GenreServiceMongo.class,
    AuthorServiceMongo.class})
class BookServiceMongoTest {

//  @MockBean
//  private BookRepository bookRepository;
//
//  @MockBean
//  private GenreService genreService;
//
//  @MockBean
//  private AuthorService authorService;
//
//  @Autowired
//  private BookServiceMongo bookServiceMongo;
//
//
//  @DisplayName("should return all books")
//  @Test
//  void shouldGetAllBooksTest() {
//
//    given(bookRepository.getAllWithBooksAndGenres()).willReturn(makeCorrectAllBooksList());
//
//    var books = bookServiceMongo.getAllWithAuthorsAndGenres();
//
//    assertThat(books).isEqualTo(makeCorrectAllBooksList());
//    Mockito.verify(bookRepository, times(1)).getAllWithBooksAndGenres();
//  }
//
//
//  @DisplayName("should return correct count")
//  @Test
//  void shouldReturnCorrectCountTest() {
//
//    given(bookRepository.count()).willReturn(3L);
//
//    var count = bookServiceMongo.count();
//
//    assertThat(count).isEqualTo(makeCorrectAllBooksList().size());
//    Mockito.verify(bookRepository, times(1)).count();
//
//  }
//
//  @DisplayName("should return correct book by id")
//  @Test
//  void shouldReturnCorrectBookById() {
//
//    var id = 1;
//
//    var books = makeCorrectAllBooksList();
//    var correctbook = getBookByIdFromList(books, id);
//
//    given(bookRepository.getById(id)).willReturn(Optional.ofNullable(correctbook));
//
//    var book = bookServiceMongo.getById(id);
//
//    assertThat(book).isPresent().get().isEqualTo(correctbook);
//    Mockito.verify(bookRepository, times(1)).getById(id);
//
//  }
//
//  @DisplayName("should correctly insert book in db")
//  @Test
//  void shouldCorrectlyInsertBook() {
//
//    var authors = new HashSet<Author>();
//    var genres = new HashSet<Genre>();
//
//    var bookName = "book4";
//
//    var id = 1L;
//    var correctInsertedId = 4L;
//    var listOfIds = new long[]{id};
//    var author = new Author(id, "author1");
//    var genre = new Genre(id, "genre1");
//    authors.add(author);
//    genres.add(genre);
//
//    var bookToInsert = new Book(0L, bookName, authors, genres, new HashSet<>());
//    var insertedBook = new Book(correctInsertedId, bookName, authors, genres, new HashSet<>());
//
//    given(bookRepository.save(bookToInsert)).willReturn(insertedBook);
//    given(genreService.getById(id)).willReturn(Optional.of(genre));
//    given(authorService.getById(id)).willReturn(Optional.of(author));
//
//    var actual = bookServiceMongo.insertOrUpdate(bookToInsert, listOfIds, listOfIds);
//
//    assertThat(actual).isEqualTo(insertedBook);
//    Mockito.verify(bookRepository, times(1)).save(bookToInsert);
//
//  }
//
//  @DisplayName("should correctly delete book by id")
//  @Test
//  void shouldCorrectlyDeleteBookById() {
//
//    var id = 1L;
//    var toDelete = new Book(id, "name", new HashSet<>(), new HashSet<>(), new HashSet<>());
//
//    given(bookRepository.getById(id)).willReturn(Optional.of(toDelete));
//
//    bookServiceMongo.deleteById(id);
//
//    Mockito.verify(bookRepository, times(1)).delete(toDelete);
//
//  }
//
//  @DisplayName("should correctly delete book by id")
//  @Test
//  void shouldThrowExceptionWhenDeleteNonExistingBookById() {
//
//    var id = 999L;
//
//    given(bookRepository.getById(id)).willReturn(Optional.empty());
//
//    assertThatThrownBy(() -> bookServiceMongo.deleteById(id))
//        .isInstanceOf(EntityNotFoundException.class);
//
//    Mockito.verify(bookRepository, times(0)).delete(any());
//
//  }
//
//
//  @DisplayName("should update book by id")
//  @Test
//  void shouldCorrectlyUpdateBook() {
//
//    var authors = new HashSet<Author>();
//    var genres = new HashSet<Genre>();
//    var id = 1L;
//    var listOfIds = new long[]{id};
//    var author = new Author(id, "author1");
//    var genre = new Genre(id, "genre1");
//    authors.add(author);
//    genres.add(genre);
//
//    var bookToUpdate = new Book(id, "newName", authors, genres, new HashSet<>());
//
//    given(bookRepository.getById(id)).willReturn(Optional.of(bookToUpdate));
//
//    bookServiceMongo.update(bookToUpdate, listOfIds, listOfIds);
//
//    Mockito.verify(bookRepository, times(1)).save(bookToUpdate);
//
//  }
//
//  @DisplayName("should throw exception when updating non existing book")
//  @Test
//  void shouldThrowExceptionWhenUpdatingNonExistingBook() {
//
//    var id = 1L;
//    var listOfIds = new long[]{id};
//    var author = new Author(id, "author1");
//    var genre = new Genre(id, "genre1");
//    var bookToUpdate = new Book(id, "newName", Set.of(author), Set.of(genre), new HashSet<>());
//
//    given(bookRepository.getById(id)).willReturn(Optional.empty());
//
//    assertThatThrownBy(() -> bookServiceMongo.update(bookToUpdate, listOfIds, listOfIds))
//        .isInstanceOf(EntityNotFoundException.class);
//
//    Mockito.verify(bookRepository, times(1)).getById(id);
//    Mockito.verify(bookRepository, times(0)).save(any());
//
//  }
//
//  @DisplayName("should throw exception when updating book with non existing genre")
//  @Test
//  void shouldThrowExceptionWhenUpdatingNonExistingGenreInBook() {
//
//    var id = 1L;
//    var listOfIds = new long[]{id};
//    var author = new Author(id, "author1");
//    var genre = new Genre(id, "genre1");
//    var bookToUpdate = new Book(id, "newName", Set.of(author), Set.of(genre), new HashSet<>());
//
//    given(bookRepository.getById(id)).willReturn(Optional.empty());
//    given(genreService.getById(id)).willReturn(Optional.empty());
//
//    assertThatThrownBy(() -> bookServiceMongo.update(bookToUpdate, listOfIds, listOfIds))
//        .isInstanceOf(EntityNotFoundException.class);
//
//    Mockito.verify(bookRepository, times(1)).getById(id);
//    Mockito.verify(bookRepository, times(0)).save(any());
//
//  }
//
//
//  private Book getBookByIdFromList(List<Book> books, long id) {
//
//    var nonExistingId = 999999L;
//    var bookOptional = books.stream().filter(book -> book.getId() == id)
//        .findFirst();
//
//    return bookOptional.orElse(
//        new Book(nonExistingId, "nonexisting",
//            new HashSet<>(),
//            new HashSet<>(),
//            new HashSet<>()));
//
//  }
//
//  private List<Book> makeCorrectAllBooksList() {
//
//    var book1 = new Book(1L, "book1",
//        Set.of(new Author(1L, "author1"), new Author(2L, "author2")),
//        Set.of(new Genre(1L, "genre1"), new Genre(2L, "genre2")),
//        new HashSet<>());
//
//    var book2 = new Book(2L, "book2",
//        Set.of(new Author(2L, "author2"), new Author(3L, "author3")),
//        Set.of(new Genre(2L, "genre2"), new Genre(3L, "genre3")),
//        new HashSet<>());
//
//    var book3 = new Book(3L, "book3",
//        Set.of(new Author(1L, "author1"), new Author(3L, "author3")),
//        Set.of(new Genre(1L, "genre1"), new Genre(3L, "genre3")),
//        new HashSet<>());
//
//    Set<Commentary> commentariesBook1 = Set.of(
//        new Commentary(1L, "com1", book1),
//        new Commentary(2L, "com2", book1),
//        new Commentary(3L, "com3", book1));
//    Set<Commentary> commentariesBook2 = Set.of(
//        new Commentary(4L, "com4", book2),
//        new Commentary(5L, "com5", book2),
//        new Commentary(6L, "com6", book2));
//
//    book1.setCommentaries(commentariesBook1);
//    book2.setCommentaries(commentariesBook2);
//
//    return List.of(
//        book3,
//        book2,
//        book3
//    );
//  }


}
