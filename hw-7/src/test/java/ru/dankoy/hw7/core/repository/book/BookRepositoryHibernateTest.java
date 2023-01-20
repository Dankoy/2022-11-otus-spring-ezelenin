package ru.dankoy.hw7.core.repository.book;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.dankoy.hw7.core.domain.Author;
import ru.dankoy.hw7.core.domain.Book;
import ru.dankoy.hw7.core.domain.Commentary;
import ru.dankoy.hw7.core.domain.Genre;


@DisplayName("Test BookDaoJdbcHibernate ")
@DataJpaTest
@Import({BookRepositoryHibernate.class})
class BookRepositoryHibernateTest {

  @Autowired
  private BookRepositoryHibernate bookDaoHibernate;

  @Autowired
  private TestEntityManager testEntityManager;


  @DisplayName("should return all books")
  @Test
  void shouldGetAllBooksTest() {
    var books = bookDaoHibernate.getAll();

    assertThat(books).isEqualTo(makeCorrectAllBooksList());
  }


  @DisplayName("should return correct count")
  @Test
  void shouldReturnCorrectCountTest() {

    var count = bookDaoHibernate.count();

    assertThat(count).isEqualTo(makeCorrectAllBooksList().size());

  }

  @DisplayName("should return correct book by id")
  @Test
  void shouldReturnCorrectBookById() {

    var id = 1;

    var books = makeCorrectAllBooksList();
    var correctbook = getBookByIdFromList(books, id);

    var book = bookDaoHibernate.getById(id);

    assertThat(book).isPresent().get().isEqualTo(correctbook);

  }

  @DisplayName("should correctly insert book in db")
  @Test
  void shouldCorrectlyInsertBook() {

    var bookName = "newName";

    var id = 1L;
    var author = new Author(id, "author1");
    var genre = new Genre(id, "genre1");
    var bookToInsert = new Book(0L, bookName, Set.of(author), Set.of(genre), new HashSet<>());

    var insertedBook = bookDaoHibernate.insertOrUpdate(bookToInsert);
    testEntityManager.flush();

    var expected = new Book(insertedBook.getId(), bookName, Set.of(author), Set.of(genre),
        new HashSet<>());

    testEntityManager.detach(insertedBook);

    var actual = bookDaoHibernate.getById(insertedBook.getId());

    assertThat(actual).isPresent().get().isEqualTo(expected);

  }

  @DisplayName("should correctly delete book by id")
  @Test
  void shouldCorrectlyDeleteBookById() {

    var id = 1L;

    var book = testEntityManager.find(Book.class, id);

    bookDaoHibernate.delete(book);
    testEntityManager.flush();

    var actual = bookDaoHibernate.getById(id);

    assertThat(actual).isNotPresent();

  }

  @DisplayName("should update book by id")
  @Test
  void shouldCorrectlyUpdateBook() {

    var id = 1L;
    var bookToUpdate = new Book(id, "newName", new HashSet<>(), new HashSet<>(), new HashSet<>());

    var updated = bookDaoHibernate.update(bookToUpdate);
    testEntityManager.flush();
    testEntityManager.detach(updated);

    var fromDb = testEntityManager.find(Book.class, id);
    testEntityManager.flush();

    assertThat(fromDb).isEqualTo(updated);

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
