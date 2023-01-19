package ru.dankoy.hw7.core.repository.genre;


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
import ru.dankoy.hw7.core.domain.Genre;


@DisplayName("Test GenreDaoHibernate ")
@DataJpaTest
@Import(GenreDaoHibernate.class)
class GenreRepositoryHibernateTest {

  @Autowired
  private GenreDaoHibernate genreDaoHibernate;

  @Autowired
  private TestEntityManager testEntityManager;


  @DisplayName("should return all genres")
  @Test
  void shouldGetAllGenresTest() {
    var genres = genreDaoHibernate.getAll();

    assertThat(genres).isEqualTo(makeCorrectAllGenresList());
  }


  @DisplayName("should return correct count")
  @Test
  void shouldReturnCorrectCountTest() {

    var count = genreDaoHibernate.count();

    assertThat(count).isEqualTo(makeCorrectAllGenresList().size());

  }

  @DisplayName("should return correct genre by id")
  @Test
  void shouldReturnCorrectGenreById() {

    var id = 1;

    var genres = makeCorrectAllGenresList();
    var correctgenre = getGenreByIdFromList(genres, id);

    var genre = genreDaoHibernate.getById(id);

    assertThat(genre).isPresent().get().isEqualTo(correctgenre);

  }

  @DisplayName("should correctly insert genre in db")
  @Test
  void shouldCorrectlyInsertGenre() {

    var genreName = "genre4";

    var genreToInsert = new Genre(0L, genreName);
    var inserted = genreDaoHibernate.insertOrUpdate(genreToInsert);

    testEntityManager.detach(inserted);

    var actual = genreDaoHibernate.getById(inserted.getId());

    assertThat(actual).isPresent().get().isEqualTo(inserted);

  }

  @DisplayName("should correctly delete genre by id")
  @Test
  void shouldCorrectlyDeleteGenreById() {

    var id = 4L;

    var genre = testEntityManager.find(Genre.class, id);

    genreDaoHibernate.delete(genre);

    testEntityManager.flush();

    var actual = genreDaoHibernate.getById(id);

    assertThat(actual).isNotPresent();
  }

  @DisplayName("should throw persistence exception when deleting genre that is used in many-to-many table")
  @Test
  void shouldThrowPersistenceExceptionWhenDeleteAuthorById() {

    var id = 1L;

    var genre = testEntityManager.find(Genre.class, id);

    assertThatThrownBy(() -> {
      genreDaoHibernate.delete(genre);
      testEntityManager.flush();
    }).isInstanceOf(PersistenceException.class);

  }


  private Genre getGenreByIdFromList(List<Genre> genres, long id) {

    var genreOptional = genres.stream().filter(genre -> genre.getId() == id)
        .findFirst();

    return genreOptional.orElse(new Genre(999999L, "nonexisting"));

  }

  private List<Genre> makeCorrectAllGenresList() {
    return List.of(
        new Genre(1L, "genre1"),
        new Genre(2L, "genre2"),
        new Genre(3L, "genre3"),
        new Genre(4L, "genre_without_book")
    );
  }


}
