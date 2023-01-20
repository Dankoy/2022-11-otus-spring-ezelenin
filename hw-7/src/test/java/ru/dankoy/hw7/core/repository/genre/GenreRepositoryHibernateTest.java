package ru.dankoy.hw7.core.repository.genre;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.dankoy.hw7.core.domain.Genre;


@DisplayName("Test GenreDaoHibernate ")
@DataJpaTest
@Import(GenreRepositoryImpl.class)
class GenreRepositoryHibernateTest {

  @Autowired
  private GenreRepositoryImpl genreRepository;

  @Autowired
  private TestEntityManager testEntityManager;

  @DisplayName("should return correct genre by id")
  @Test
  void shouldReturnCorrectGenreById() {

    var id = 1L;

    var expected = testEntityManager.find(Genre.class, id);
    testEntityManager.detach(expected);

    var genre = genreRepository.getById(id);

    assertThat(genre).isPresent().get().isEqualTo(expected);

  }


}
