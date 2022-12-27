package ru.dankoy.hw5.core.dao.author;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.dankoy.hw5.core.domain.Author;
import ru.dankoy.hw5.core.exceptions.AuthorDaoException;


@DisplayName("Test AuthorDaoJdbc ")
@JdbcTest
@Import(AuthorDaoJdbc.class)
class AuthorDaoJdbcTest {

  @Autowired
  private AuthorDaoJdbc authorDaoJdbc;


  @DisplayName("should return all authors")
  @Test
  void shouldGetAllAuthorsTest() {
    var authors = authorDaoJdbc.getAll();

    assertThat(authors).isEqualTo(makeCorrectAllAuthorsList());
  }


  @DisplayName("should return correct count")
  @Test
  void shouldReturnCorrectCountTest() {

    var count = authorDaoJdbc.count();

    assertThat(count).isEqualTo(makeCorrectAllAuthorsList().size());

  }

  @DisplayName("should return correct author by id")
  @Test
  void shouldReturnCorrectAuthorById() {

    var id = 1;

    var authors = makeCorrectAllAuthorsList();
    var correctAuthor = getAuthorByIdFromList(authors, id);

    var author = authorDaoJdbc.getById(id);

    assertThat(author).isEqualTo(correctAuthor);

  }

  @DisplayName("should throw AuthorDaoException for non existing author")
  @Test
  void shouldThrowAuthorDaoExceptionWhenGetById() {

    var id = 999L;

    assertThatThrownBy(() -> authorDaoJdbc.getById(id))
        .isInstanceOf(AuthorDaoException.class)
        .hasMessage(String.format("Author with id '%d' does not exist", id));

  }

  @DisplayName("should correctly insert author in db")
  @Test
  void shouldCorrectlyInsertAuthor() {

    var authorName = "author4";

    var id = authorDaoJdbc.insert(authorName);

    var expected = new Author(id, authorName);

    var actual = authorDaoJdbc.getById(id);

    assertThat(expected).isEqualTo(actual);

  }

  @DisplayName("should correctly delete author by id")
  @Test
  void shouldCorrectlyDeleteAuthorById() {

    var id = 1L;

    assertThatCode(() -> authorDaoJdbc.getById(id))
        .doesNotThrowAnyException();

    authorDaoJdbc.deleteById(id);

    assertThatThrownBy(() -> authorDaoJdbc.getById(id))
        .isInstanceOf(AuthorDaoException.class)
        .hasMessage(String.format("Author with id '%d' does not exist", id));

  }

  @DisplayName("should correctly delete author by id")
  @Test
  void shouldThrowExceptionWhenDeleteNonExistingAuthorById() {

    var id = 999L;

    assertThatThrownBy(() -> authorDaoJdbc.deleteById(id))
        .isInstanceOf(AuthorDaoException.class)
        .hasMessage(String.format("Can't delete author. Author with id '%d' does not exist", id));

  }


  @DisplayName("should update author by id")
  @Test
  void shouldCorrectlyUpdateAuthor() {

    var id = 1;
    var authorToUpdate = new Author(1, "newName");

    authorDaoJdbc.update(authorToUpdate);

    var fromDb = authorDaoJdbc.getById(id);

    assertThat(fromDb).isEqualTo(authorToUpdate);

  }


  private Author getAuthorByIdFromList(List<Author> authors, long id) {

    var authorOptional = authors.stream().filter(author -> author.getId() == id)
        .findFirst();

    return authorOptional.orElse(new Author(999999L, "nonexisting"));

  }

  private List<Author> makeCorrectAllAuthorsList() {
    return List.of(
        new Author(1L, "author1"),
        new Author(2L, "author2"),
        new Author(3L, "author3")
    );
  }


}
