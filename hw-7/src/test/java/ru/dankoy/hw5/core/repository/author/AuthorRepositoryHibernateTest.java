package ru.dankoy.hw5.core.repository.author;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.List;
import javax.persistence.PersistenceException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.dankoy.hw5.core.domain.Author;


@DisplayName("Test AuthorDaoHibernate ")
@DataJpaTest
@Import(AuthorDaoHibernate.class)
class AuthorRepositoryHibernateTest {

  @Autowired
  private AuthorDaoHibernate authorDaoHibernate;

  @Autowired
  private TestEntityManager testEntityManager;

  @DisplayName("should return all authors")
  @Test
  void shouldGetAllAuthorsTest() {
    var authors = authorDaoHibernate.getAll();

    assertThat(authors).isEqualTo(makeCorrectAllAuthorsList());
  }


  @DisplayName("should return correct count")
  @Test
  void shouldReturnCorrectCountTest() {

    var count = authorDaoHibernate.count();

    assertThat(count).isEqualTo(makeCorrectAllAuthorsList().size());

  }

  @DisplayName("should return correct author by id")
  @Test
  void shouldReturnCorrectAuthorById() {

    var id = 1;

    var authors = makeCorrectAllAuthorsList();
    var correctAuthor = getAuthorByIdFromList(authors, id);

    var author = authorDaoHibernate.getById(id);

    assertThat(author).isPresent().get().isEqualTo(correctAuthor);

  }

  @DisplayName("should correctly insert author in db")
  @Test
  void shouldCorrectlyInsertAuthor() {

    var authorName = "author4";

    var authorToInsert = new Author(0L, authorName);
    var insertedAuthor = authorDaoHibernate.insertOrUpdate(authorToInsert);

    testEntityManager.detach(insertedAuthor);

    var actual = authorDaoHibernate.getById(insertedAuthor.getId());

    assertThat(actual).isPresent().get().isEqualTo(authorToInsert);

  }

  @DisplayName("should correctly delete author by id")
  @Test
  void shouldCorrectlyDeleteAuthorById() {

    var id = 4L;

    var author = testEntityManager.find(Author.class, id);

    authorDaoHibernate.delete(author);

    testEntityManager.flush();

    var actual = authorDaoHibernate.getById(id);

    assertThat(actual).isNotPresent();

  }

  @DisplayName("should throw persistence exception exception when deleting author that is used in many-to-many table")
  @Test
  void shouldThrowPersistenceExceptionWhenDeleteAuthorById() {

    var id = 1L;
    var author = testEntityManager.find(Author.class, id);

    assertThatThrownBy(() -> {
      authorDaoHibernate.delete(author);
      testEntityManager.flush();
    }).isInstanceOf(PersistenceException.class);

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
        new Author(3L, "author3"),
        new Author(4L, "author_without_book")
    );
  }


}
