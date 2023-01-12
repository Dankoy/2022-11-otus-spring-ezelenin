package ru.dankoy.hw5.core.dao.genre;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DataIntegrityViolationException;
import ru.dankoy.hw5.core.domain.Genre;
import ru.dankoy.hw5.core.exceptions.GenreDaoException;


@DisplayName("Test GenreDaoJdbc ")
@JdbcTest
@Import(GenreDaoHibernate.class)
class GenreDaoHibernateTest {

  @Autowired
  private GenreDaoHibernate genreDaoHibernate;


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

    assertThat(genre).isEqualTo(correctgenre);

  }

  @DisplayName("should throw genreDaoException for non existing genre")
  @Test
  void shouldThrowGenreDaoExceptionWhenGetById() {

    var id = 999;

    assertThatThrownBy(() -> genreDaoHibernate.getById(id))
        .isInstanceOf(GenreDaoException.class);
  }

  @DisplayName("should correctly insert genre in db")
  @Test
  void shouldCorrectlyInsertGenre() {

    var genreName = "genre4";

    var id = genreDaoHibernate.insertOrUpdate(genreName);

    var expected = new Genre(id, genreName);

    var actual = genreDaoHibernate.getById(id);

    assertThat(expected).isEqualTo(actual);

  }

  @DisplayName("should correctly delete genre by id")
  @Test
  void shouldCorrectlyDeleteGenreById() {

    var id = 4L;

    assertThatCode(() -> genreDaoHibernate.getById(id))
        .doesNotThrowAnyException();

    genreDaoHibernate.deleteById(id);

    assertThatThrownBy(() -> genreDaoHibernate.getById(id))
        .isInstanceOf(GenreDaoException.class);
  }

  @DisplayName("should throw data integrity exception when deleting genre that is used in many-to-many table")
  @Test
  void shouldThrowDataIntegrityViolationExceptionWhenDeleteAuthorById() {

    var id = 1L;

    assertThatCode(() -> genreDaoHibernate.getById(id))
        .doesNotThrowAnyException();

    assertThatThrownBy(() -> genreDaoHibernate.deleteById(id))
        .isInstanceOf(DataIntegrityViolationException.class);

  }

  @DisplayName("should not delete genre by id")
  @Test
  void shouldNotDeleteNonExistingGenreById() {

    var id = 999L;
    var countBefore = genreDaoHibernate.count();
    genreDaoHibernate.deleteById(id);

    var countAfter = genreDaoHibernate.count();

    assertThat(countBefore - countAfter).isZero();

  }


  @DisplayName("should update genre by id")
  @Test
  void shouldCorrectlyUpdateGenre() {

    var id = 1L;
    var genreToUpdate = new Genre(1, "newName");

    genreDaoHibernate.update(genreToUpdate);

    var fromDb = genreDaoHibernate.getById(id);

    assertThat(fromDb).isEqualTo(genreToUpdate);

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
