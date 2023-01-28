package ru.dankoy.hw8.core.repository.book;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import ru.dankoy.hw8.core.domain.Book;


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

}
