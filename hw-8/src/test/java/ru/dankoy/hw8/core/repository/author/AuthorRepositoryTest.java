package ru.dankoy.hw8.core.repository.author;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.dankoy.hw8.core.domain.Author;


@DisplayName("Test AuthorRepository ")
@DataJpaTest
class AuthorRepositoryTest {

  @Autowired
  private AuthorRepositoryImpl authorRepository;

  @Autowired
  private TestEntityManager testEntityManager;


  @DisplayName("should return correct author by id")
  @Test
  void shouldReturnCorrectAuthorById() {

    var id = 1L;

    var expected = testEntityManager.find(Author.class, id);
    testEntityManager.detach(expected);

    var author = authorRepository.getById(id);

    assertThat(author).isPresent().get().isEqualTo(expected);

  }


}
