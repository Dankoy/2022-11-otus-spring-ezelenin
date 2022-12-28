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
import ru.dankoy.hw5.core.domain.Genre;
import ru.dankoy.hw5.core.exceptions.GenreDaoException;


@DisplayName("Test GenreDaoJdbc ")
@JdbcTest
@Import(GenreDaoJdbc.class)
class GenreDaoJdbcTest {

  @Autowired
  private GenreDaoJdbc genreDaoJdbc;


  @DisplayName("should return all genres")
  @Test
  void shouldGetAllGenresTest() {
    var genres = genreDaoJdbc.getAll();

    assertThat(genres).isEqualTo(makeCorrectAllGenresList());
  }


  @DisplayName("should return correct count")
  @Test
  void shouldReturnCorrectCountTest() {

    var count = genreDaoJdbc.count();

    assertThat(count).isEqualTo(makeCorrectAllGenresList().size());

  }

  @DisplayName("should return correct genre by id")
  @Test
  void shouldReturnCorrectGenreById() {

    var id = 1;

    var genres = makeCorrectAllGenresList();
    var correctgenre = getGenreByIdFromList(genres, id);

    var genre = genreDaoJdbc.getById(id);

    assertThat(genre).isEqualTo(correctgenre);

  }

  @DisplayName("should throw genreDaoException for non existing genre")
  @Test
  void shouldThrowGenreDaoExceptionWhenGetById() {

    var id = 999;

    assertThatThrownBy(() -> genreDaoJdbc.getById(id))
        .isInstanceOf(GenreDaoException.class)
        .hasMessage(String.format("Genre with id '%d' does not exist", id));

  }

  @DisplayName("should correctly insert genre in db")
  @Test
  void shouldCorrectlyInsertGenre() {

    var genreName = "genre4";

    var id = genreDaoJdbc.insert(genreName);

    var expected = new Genre(id, genreName);

    var actual = genreDaoJdbc.getById(id);

    assertThat(expected).isEqualTo(actual);

  }

  @DisplayName("should correctly delete genre by id")
  @Test
  void shouldCorrectlyDeleteGenreById() {

    var id = 4L;

    assertThatCode(() -> genreDaoJdbc.getById(id))
        .doesNotThrowAnyException();

    genreDaoJdbc.deleteById(id);

    assertThatThrownBy(() -> genreDaoJdbc.getById(id))
        .isInstanceOf(GenreDaoException.class)
        .hasMessage(String.format("Genre with id '%d' does not exist", id));

  }

  @DisplayName("should correctly delete genre by id")
  @Test
  void shouldThrowExceptionWhenDeleteNonExistingGenreById() {

    var id = 999L;

    assertThatThrownBy(() -> genreDaoJdbc.deleteById(id))
        .isInstanceOf(GenreDaoException.class)
        .hasMessage(String.format("Can't delete genre. Genre with id '%d' does not exist", id));

  }


  @DisplayName("should update genre by id")
  @Test
  void shouldCorrectlyUpdateGenre() {

    var id = 1L;
    var genreToUpdate = new Genre(1, "newName");

    genreDaoJdbc.update(genreToUpdate);

    var fromDb = genreDaoJdbc.getById(id);

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
