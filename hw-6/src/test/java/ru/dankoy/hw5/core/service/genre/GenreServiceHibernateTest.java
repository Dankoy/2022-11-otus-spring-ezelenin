package ru.dankoy.hw5.core.service.genre;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.dankoy.hw5.core.dao.genre.GenreDao;
import ru.dankoy.hw5.core.domain.Genre;
import ru.dankoy.hw5.core.exceptions.EntityNotFoundException;


@Transactional(propagation = Propagation.NEVER)
@DisplayName("Test GenreServiceHibernate ")
@JdbcTest
@Import({GenreServiceHibernate.class, GenreDao.class})
class GenreServiceHibernateTest {

  @MockBean
  private GenreDao genreDao;

  @Autowired
  private GenreServiceHibernate genreServiceHibernate;


  @DisplayName("should return all genres")
  @Test
  void shouldGetAllGenresTest() {

    given(genreDao.getAll()).willReturn(makeCorrectAllGenresList());

    var genres = genreServiceHibernate.getAll();

    assertThat(genres).isEqualTo(makeCorrectAllGenresList());
    Mockito.verify(genreDao, times(1)).getAll();
  }


  @DisplayName("should return correct count")
  @Test
  void shouldReturnCorrectCountTest() {

    given(genreDao.count()).willReturn(3L);
    var count = genreServiceHibernate.count();

    assertThat(count).isEqualTo(makeCorrectAllGenresList().size());
    Mockito.verify(genreDao, times(1)).count();

  }

  @DisplayName("should return correct genre by id")
  @Test
  void shouldReturnCorrectGenreById() {

    var id = 1;

    var genres = makeCorrectAllGenresList();
    var correctgenre = getGenreByIdFromList(genres, id);

    given(genreDao.getById(id)).willReturn(Optional.ofNullable(correctgenre));

    var genre = genreServiceHibernate.getById(id);

    assertThat(genre).isPresent().get().isEqualTo(correctgenre);
    Mockito.verify(genreDao, times(1)).getById(id);

  }

  @DisplayName("should correctly insert genre in db")
  @Test
  void shouldCorrectlyInsertGenre() {

    var genreName = "genre4";
    var insertedId = 4L;

    var genreToInsert = new Genre(0L, genreName);
    var insertedGenre = new Genre(insertedId, genreName);

    given(genreDao.insertOrUpdate(genreToInsert)).willReturn(insertedGenre);

    var actual = genreServiceHibernate.insert(genreToInsert);

    assertThat(actual).isEqualTo(insertedGenre);
    Mockito.verify(genreDao, times(1)).insertOrUpdate(genreToInsert);

  }

  @DisplayName("should correctly delete genre by id")
  @Test
  void shouldCorrectlyDeleteGenreById() {

    var id = 1L;

    var toDelete = new Genre(id, "name");
    given(genreDao.getById(id)).willReturn(Optional.of(toDelete));
    genreServiceHibernate.deleteById(id);

    Mockito.verify(genreDao, times(1)).delete(toDelete);

  }

  @DisplayName("should throw exception when delete genre by id")
  @Test
  void shouldThrowExceptionWhenDeleteNonExistingGenreById() {

    var id = 999L;

    given(genreDao.getById(id)).willReturn(Optional.empty());

    assertThatThrownBy(() -> genreServiceHibernate.deleteById(id))
        .isInstanceOf(EntityNotFoundException.class);

    Mockito.verify(genreDao, times(0)).delete(any());

  }


  @DisplayName("should update genre by id")
  @Test
  void shouldCorrectlyUpdateGenre() {

    var id = 1L;
    var genreToUpdate = new Genre(id, "newName");

    genreServiceHibernate.update(genreToUpdate);

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
