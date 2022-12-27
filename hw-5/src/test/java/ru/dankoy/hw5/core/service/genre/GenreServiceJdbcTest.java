package ru.dankoy.hw5.core.service.genre;


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
import ru.dankoy.hw5.core.dao.genre.GenreDao;
import ru.dankoy.hw5.core.domain.Genre;
import ru.dankoy.hw5.core.exceptions.GenreDaoException;


@DisplayName("Test GenreServiceJdbc ")
@JdbcTest
@Import({GenreServiceJdbc.class, GenreDao.class})
class GenreServiceJdbcTest {

  @MockBean
  private GenreDao genreDao;

  @Autowired
  private GenreServiceJdbc genreServiceJdbc;


  @DisplayName("should return all genres")
  @Test
  void shouldGetAllGenresTest() {

    given(genreDao.getAll()).willReturn(makeCorrectAllGenresList());

    var genres = genreServiceJdbc.getAll();

    assertThat(genres).isEqualTo(makeCorrectAllGenresList());
    Mockito.verify(genreDao, times(1)).getAll();
  }


  @DisplayName("should return correct count")
  @Test
  void shouldReturnCorrectCountTest() {

    given(genreDao.count()).willReturn(3L);
    var count = genreServiceJdbc.count();

    assertThat(count).isEqualTo(makeCorrectAllGenresList().size());
    Mockito.verify(genreDao, times(1)).count();

  }

  @DisplayName("should return correct genre by id")
  @Test
  void shouldReturnCorrectGenreById() {

    var id = 1;

    var genres = makeCorrectAllGenresList();
    var correctgenre = getGenreByIdFromList(genres, id);

    given(genreDao.getById(id)).willReturn(correctgenre);

    var genre = genreServiceJdbc.getById(id);

    assertThat(genre).isEqualTo(correctgenre);
    Mockito.verify(genreDao, times(1)).getById(id);

  }

  @DisplayName("should throw genreServiceException for non existing genre")
  @Test
  void shouldThrowGenreServiceExceptionWhenGetById() {

    var id = 999;
    String exceptionMessage = String.format("Genre with id '%d' does not exist", id);

    Mockito.doThrow(new GenreDaoException(exceptionMessage)).when(genreDao).getById(id);

    assertThatThrownBy(() -> genreServiceJdbc.getById(id))
        .isInstanceOf(GenreDaoException.class)
        .hasMessage(exceptionMessage);

  }

  @DisplayName("should correctly insert genre in db")
  @Test
  void shouldCorrectlyInsertGenre() {

    var genreName = "genre4";
    var insertedId = 4L;

    given(genreDao.insert(genreName)).willReturn(insertedId);

    var id = genreServiceJdbc.insert(genreName);

    assertThat(id).isEqualTo(insertedId);
    Mockito.verify(genreDao, times(1)).insert(genreName);

  }

  @DisplayName("should correctly delete genre by id")
  @Test
  void shouldCorrectlyDeleteGenreById() {

    var id = 1L;

    genreServiceJdbc.deleteById(id);

    Mockito.verify(genreDao, times(1)).deleteById(id);

  }

  @DisplayName("should correctly delete genre by id")
  @Test
  void shouldThrowExceptionWhenDeleteNonExistingGenreById() {

    var id = 999L;
    var exceptionMessage = String.format("Can't delete genre. Genre with id '%d' does not exist",
        id);

    Mockito.doThrow(new GenreDaoException(exceptionMessage)).when(genreDao).deleteById(id);

    assertThatThrownBy(() -> genreServiceJdbc.deleteById(id))
        .isInstanceOf(GenreDaoException.class)
        .hasMessage(exceptionMessage);

    Mockito.verify(genreDao, times(1)).deleteById(id);

  }


  @DisplayName("should update genre by id")
  @Test
  void shouldCorrectlyUpdateGenre() {

    var id = 1L;
    var genreToUpdate = new Genre(id, "newName");

    genreServiceJdbc.update(genreToUpdate);

    Mockito.verify(genreDao, times(1)).update(genreToUpdate);

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
        new Genre(3L, "genre3")
    );
  }


}
