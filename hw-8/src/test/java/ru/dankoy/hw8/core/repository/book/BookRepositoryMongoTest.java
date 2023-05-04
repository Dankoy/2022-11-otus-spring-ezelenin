package ru.dankoy.hw8.core.repository.book;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import ru.dankoy.hw8.core.domain.Author;
import ru.dankoy.hw8.core.domain.Book;
import ru.dankoy.hw8.core.exceptions.EntityNotFoundException;


@DisplayName("Test BookRepositoryMongo ")
@DataMongoTest
class BookRepositoryMongoTest {

  @Autowired
  private BookRepository bookRepository;

  @Autowired
  private MongoTemplate mongoTemplate;


  @DisplayName("should return all books by genre name")
  @Test
  void shouldGetAllBooksByGenreNameTest() {
    var books = bookRepository.findBookByGenres("genre1");

    Query query = new Query();
    query.addCriteria(Criteria.where("genres.name").is("genre1"));

    var booksExpected = mongoTemplate.find(query, Book.class);

    assertThat(books).isEqualTo(booksExpected);
  }

  @DisplayName("should return no books by genre name")
  @Test
  void shouldGetNoBooksByGenreNameTest() {
    var books = bookRepository.findBookByGenres("none-existing");

    Query query = new Query();
    query.addCriteria(Criteria.where("genres.name").is("none-existing"));

    var booksExpected = mongoTemplate.find(query, Book.class);

    assertThat(books).isEqualTo(booksExpected);
  }


  @DisplayName("should return no books by genre name")
  @Test
  void shouldGetAllGenresByBookIdTest() {

    var book1 = mongoTemplate.find(
            new Query().addCriteria(Criteria.where("name").is("book1")),
            Book.class)
        .get(0);

    var genres = bookRepository.getAllGenresByBookId(book1.getId());

    assertThat(genres).containsExactlyInAnyOrderElementsOf(book1.getGenres());
  }


  @DisplayName("should correctly save book")
  @Test
  void shouldCorrectlySaveBook() {

    var author = mongoTemplate.find(
            new Query().addCriteria(Criteria.where("name").is("author1")),
            Author.class
        )
        .get(0);
    var book = new Book(null, "mybookname", Set.of(author), new HashSet<>());

    var inserted = bookRepository.saveAndCheckAuthors(book);

    var expected = mongoTemplate.find(
        new Query().addCriteria(Criteria.where("name").is("mybookname")),
        Book.class
    ).get(0);

    assertThat(inserted).isEqualTo(expected);

  }


  @DisplayName("should throw exception when save book with non existing author")
  @Test
  void shouldThrowExceptionWhenSaveBookWithNonExistingAuthor() {

    var author = new Author("blah", "blah");
    var book = new Book(null, "mybookname", Set.of(author), new HashSet<>());

    assertThatThrownBy(() -> bookRepository.saveAndCheckAuthors(book))
        .isInstanceOf(EntityNotFoundException.class);

  }

}
