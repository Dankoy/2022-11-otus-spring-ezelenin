package ru.dankoy.hw5.core.service.author;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import ru.dankoy.hw5.core.dao.author.AuthorDao;
import ru.dankoy.hw5.core.domain.Author;
import ru.dankoy.hw5.core.exceptions.AuthorDaoException;


@DisplayName("Test AuthorServiceJdbc ")
@JdbcTest
@Import({AuthorServiceJdbc.class, AuthorDao.class})
class AuthorServiceJdbcTest {


  @MockBean
  private AuthorDao authorDao;

  @Autowired
  private AuthorServiceJdbc authorServiceJdbc;


  @DisplayName("should return all authors")
  @Test
  void shouldGetAllAuthorsTest() {

    given(authorDao.getAll()).willReturn(makeCorrectAllAuthorsList());

    var authors = authorServiceJdbc.getAll();

    assertThat(authors).isEqualTo(makeCorrectAllAuthorsList());

    Mockito.verify(authorDao, times(1)).getAll();
  }


  @DisplayName("should return correct count")
  @Test
  void shouldReturnCorrectCountTest() {

    given(authorDao.count()).willReturn(3L);

    var count = authorServiceJdbc.count();

    assertThat(count).isEqualTo(makeCorrectAllAuthorsList().size());
    Mockito.verify(authorDao, times(1)).count();

  }

  @DisplayName("should return correct author by id")
  @Test
  void shouldReturnCorrectAuthorById() {

    var id = 1;

    var authors = makeCorrectAllAuthorsList();
    var correctAuthor = getAuthorByIdFromList(authors, id);

    given(authorDao.getById(id)).willReturn(correctAuthor);

    var author = authorServiceJdbc.getById(id);

    assertThat(author).isEqualTo(correctAuthor);
    Mockito.verify(authorDao, times(1)).getById(id);

  }

  @DisplayName("should throw AuthorDaoException for non existing author")
  @Test
  void shouldThrowAuthorDaoExceptionWhenGetById() {

    var id = 999L;

    Mockito.doThrow(new AuthorDaoException(new Exception())).when(authorDao).getById(id);

    assertThatThrownBy(() -> authorServiceJdbc.getById(id))
        .isInstanceOf(AuthorDaoException.class);
  }


  @DisplayName("should correctly insert author in db")
  @Test
  void shouldCorrectlyInsertAuthor() {

    var authorName = "author4";
    var insertedId = 4L;

    given(authorDao.insert(authorName)).willReturn(insertedId);

    var id = authorServiceJdbc.insert(authorName);

    assertThat(id).isEqualTo(insertedId);
    Mockito.verify(authorDao, times(1)).insert(authorName);

  }

  @DisplayName("should correctly delete author by id")
  @Test
  void shouldCorrectlyDeleteAuthorById() {

    var id = 1L;

    authorServiceJdbc.deleteById(id);

    Mockito.verify(authorDao, times(1)).deleteById(id);

  }

  @DisplayName("should correctly delete author by id")
  @Test
  void shouldThrowExceptionWhenDeleteNonExistingAuthorById() {

    var id = 999L;

    Mockito.doThrow(new AuthorDaoException(new Exception())).when(authorDao).deleteById(id);

    assertThatThrownBy(() -> authorServiceJdbc.deleteById(id))
        .isInstanceOf(AuthorDaoException.class);
    Mockito.verify(authorDao, times(1)).deleteById(id);

  }


  @DisplayName("should update author by id")
  @Test
  void shouldCorrectlyUpdateAuthor() {

    var id = 1;
    var authorToUpdate = new Author(id, "newName");

    authorServiceJdbc.update(authorToUpdate);

    Mockito.verify(authorDao, times(1)).update(authorToUpdate);

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
