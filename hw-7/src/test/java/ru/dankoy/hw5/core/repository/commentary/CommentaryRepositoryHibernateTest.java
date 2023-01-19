package ru.dankoy.hw5.core.repository.commentary;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.dankoy.hw5.core.domain.Book;
import ru.dankoy.hw5.core.domain.Commentary;

@DisplayName("Test CommentaryDaoHibernate ")
@DataJpaTest
@Import(CommentaryDaoHibernate.class)
class CommentaryRepositoryHibernateTest {


  @Autowired
  private CommentaryDaoHibernate commentaryDaoHibernate;

  @Autowired
  private TestEntityManager testEntityManager;


  @DisplayName(" should return correct comment by id")
  @Test
  void shouldReturnCorrectCommentaryById() {

    var comId = 1L;

    var expected = testEntityManager.find(Commentary.class, comId);
    testEntityManager.detach(expected);

    var actual = commentaryDaoHibernate.getById(comId);

    assertThat(actual).isPresent().get().isEqualTo(expected);

  }


  @DisplayName(" should return empty comment for non existent comment")
  @Test
  void shouldReturnEmptyCommentaryById() {

    var comId = 999L;

    var actual = commentaryDaoHibernate.getById(comId);

    assertThat(actual).isEmpty();

  }


  @DisplayName(" should correctly delete comment by id")
  @Test
  void shouldCorrectlyDeleteCommentaryById() {

    var comId = 1L;

    var toDelete = testEntityManager.find(Commentary.class, comId);

    commentaryDaoHibernate.delete(toDelete);

    testEntityManager.flush();

    var empty = commentaryDaoHibernate.getById(comId);

    assertThat(empty).isEmpty();

  }

  @DisplayName(" should correctly insert new commentary")
  @Test
  void shouldCorrectlyInsertNewCommentary() {

    var id = 1;

    var book = new Book(id, "name", new HashSet<>(), new HashSet<>(), new HashSet<>());
    var commentary = new Commentary(id, "com", book);

    var inserted = commentaryDaoHibernate.insertOrUpdate(commentary);

    testEntityManager.flush();
    testEntityManager.detach(inserted);

    var actual = testEntityManager.find(Commentary.class, inserted.getId());

    assertThat(inserted).isEqualTo(actual);

  }


  @DisplayName(" should correctly update commentary")
  @Test
  void shouldCorrectlyUpdateCommentary() {

    var id = 1;

    var book = new Book(id, "name", new HashSet<>(), new HashSet<>(), new HashSet<>());
    var newCommentary = new Commentary(id, "com", book);

    var updated = commentaryDaoHibernate.insertOrUpdate(newCommentary);

    testEntityManager.flush();
    testEntityManager.detach(updated);

    var actual = testEntityManager.find(Commentary.class, updated.getId());

    assertThat(updated).isEqualTo(actual);

  }

}
