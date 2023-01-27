package ru.dankoy.hw8.core.repository.book;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.List;
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
import ru.dankoy.hw8.core.domain.Commentary;
import ru.dankoy.hw8.core.domain.Genre;


@DisplayName("Test BookRepositoryJpa ")
@DataMongoTest
class BookRepositoryJpaTest {

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


}
