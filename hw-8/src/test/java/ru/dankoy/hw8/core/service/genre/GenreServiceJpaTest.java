package ru.dankoy.hw8.core.service.genre;


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
import ru.dankoy.hw8.core.domain.Genre;
import ru.dankoy.hw8.core.exceptions.EntityNotFoundException;
import ru.dankoy.hw8.core.repository.genre.GenreRepository;


@Transactional(propagation = Propagation.NEVER)
@DisplayName("Test GenreServiceJpa ")
@JdbcTest
@Import({GenreServiceJpa.class, GenreRepository.class})
class GenreServiceJpaTest {

  @MockBean
  private GenreRepository genreRepository;

  @Autowired
  private GenreServiceJpa genreServiceJpa;


  @DisplayName("should return all genres")
  @Test
  void shouldGetAllGenresTest() {

    given(genreRepository.findAll()).willReturn(makeCorrectAllGenresList());

    var genres = genreServiceJpa.getAll();

    assertThat(genres).isEqualTo(makeCorrectAllGenresList());
    Mockito.verify(genreRepository, times(1)).findAll();
  }


  @DisplayName("should return correct count")
  @Test
  void shouldReturnCorrectCountTest() {

    given(genreRepository.count()).willReturn(3L);
    var count = genreServiceJpa.count();

    assertThat(count).isEqualTo(makeCorrectAllGenresList().size());
    Mockito.verify(genreRepository, times(1)).count();

  }

  @DisplayName("should return correct genre by id")
  @Test
  void shouldReturnCorrectGenreById() {

    var id = 1;

    var genres = makeCorrectAllGenresList();
    var correctgenre = getGenreByIdFromList(genres, id);

    given(genreRepository.getById(id)).willReturn(Optional.ofNullable(correctgenre));

    var genre = genreServiceJpa.getById(id);

    assertThat(genre).isPresent().get().isEqualTo(correctgenre);
    Mockito.verify(genreRepository, times(1)).getById(id);

  }

  @DisplayName("should correctly insert genre in db")
  @Test
  void shouldCorrectlyInsertGenre() {

    var genreName = "genre4";
    var insertedId = 4L;

    var genreToInsert = new Genre(0L, genreName);
    var insertedGenre = new Genre(insertedId, genreName);

    given(genreRepository.save(genreToInsert)).willReturn(insertedGenre);

    var actual = genreServiceJpa.insertOrUpdate(genreToInsert);

    assertThat(actual).isEqualTo(insertedGenre);
    Mockito.verify(genreRepository, times(1)).save(genreToInsert);

  }

  @DisplayName("should correctly delete genre by id")
  @Test
  void shouldCorrectlyDeleteGenreById() {

    var id = 1L;

    var toDelete = new Genre(id, "name");
    given(genreRepository.getById(id)).willReturn(Optional.of(toDelete));
    genreServiceJpa.deleteById(id);

    Mockito.verify(genreRepository, times(1)).delete(toDelete);

  }

  @DisplayName("should throw exception when delete genre by id")
  @Test
  void shouldThrowExceptionWhenDeleteNonExistingGenreById() {

    var id = 999L;

    given(genreRepository.getById(id)).willReturn(Optional.empty());

    assertThatThrownBy(() -> genreServiceJpa.deleteById(id))
        .isInstanceOf(EntityNotFoundException.class);

    Mockito.verify(genreRepository, times(0)).delete(any());

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
