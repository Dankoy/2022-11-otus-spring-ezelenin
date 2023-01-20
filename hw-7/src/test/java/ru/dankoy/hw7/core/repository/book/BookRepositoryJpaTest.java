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
import ru.dankoy.hw7.core.domain.Author;
import ru.dankoy.hw7.core.domain.Book;
import ru.dankoy.hw7.core.domain.Commentary;
import ru.dankoy.hw7.core.domain.Genre;


@DisplayName("Test BookRepositoryJpa ")
@DataJpaTest
class BookRepositoryJpaTest {

  @Autowired
  private BookRepositoryImpl bookRepository;

  @Autowired
  private TestEntityManager testEntityManager;


  @DisplayName("should return all books")
  @Test
  void shouldGetAllBooksTest() {
    var books = bookRepository.getAllWithBooksAndGenres();

    assertThat(books).isEqualTo(makeCorrectAllBooksList());
  }


  @DisplayName("should return correct book by id")
  @Test
  void shouldReturnCorrectBookById() {

    var id = 1L;

    var expected = testEntityManager.find(Book.class, id);

    var book = bookRepository.getById(id);

    assertThat(book).isPresent().get().isEqualTo(expected);

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
