package ru.dankoy.hw7.core.repository.commentary;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.dankoy.hw7.core.domain.Commentary;

@DisplayName("Test CommentaryDaoHibernate ")
@DataJpaTest
@Import(CommentaryRepositoryImpl.class)
class CommentaryRepositoryHibernateTest {


  @Autowired
  private CommentaryRepositoryImpl commentaryRepository;

  @Autowired
  private TestEntityManager testEntityManager;


  @DisplayName(" should return correct comment by id")
  @Test
  void shouldReturnCorrectCommentaryById() {

    var comId = 1L;

    var expected = testEntityManager.find(Commentary.class, comId);
    testEntityManager.detach(expected);

    var actual = commentaryRepository.getById(comId);

    assertThat(actual).isPresent().get().isEqualTo(expected);

  }


  @DisplayName(" should return empty comment for non existent comment")
  @Test
  void shouldReturnEmptyCommentaryById() {

    var comId = 999L;

    var actual = commentaryRepository.getById(comId);

    assertThat(actual).isEmpty();

  }


}
