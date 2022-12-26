package ru.dankoy.hw5.core.dao.genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.dankoy.hw5.core.domain.Genre;
import ru.dankoy.hw5.core.exceptions.GenreDaoException;


@Repository
@RequiredArgsConstructor
public class GenreDaoJdbc implements GenreDao {


  private final NamedParameterJdbcOperations namedParameterJdbcOperations;

  @Override
  public List<Genre> getAll() {
    return namedParameterJdbcOperations.query("select id, name from genres", new GenreMapper());
  }

  @Override
  public Genre getById(long id) {
    Map<String, Object> params = Collections.singletonMap("id", id);
    try {
      return namedParameterJdbcOperations.queryForObject(
          "select id, name from genres where id = :id", params, new GenreMapper()
      );
    } catch (Exception e) {
      throw new GenreDaoException(String.format("Genre with id '%d' does not exist", id), e);
    }

  }

  @Override
  public long insert(String genreName) {
    MapSqlParameterSource parameters = new MapSqlParameterSource()
        .addValue("name", genreName);
    KeyHolder keyHolder = new GeneratedKeyHolder();
    namedParameterJdbcOperations
        .update("insert into genres (name) values (:name)", parameters, keyHolder);

    Optional<Number> optionalNumber = Optional.ofNullable(keyHolder.getKey());
    Number number = optionalNumber.orElseThrow(() -> new GenreDaoException(
        "Expecting key holder not null, but got " + keyHolder.getKey()));
    return number.longValue();

  }

  @Override
  public void deleteById(long id) {
    Map<String, Object> params = Collections.singletonMap("id", id);
    int affected = namedParameterJdbcOperations.update(
        "delete from genres where id = :id", params
    );
    if (affected == 0) {
      throw new GenreDaoException(
          String.format("Can't delete genre. Genre with id '%d' does not exist", id));
    }
  }

  @Override
  public void update(Genre genre) {
    namedParameterJdbcOperations.update(
        "update genres set name = :name where id = :id",
        Map.of("id", genre.getId(), "name", genre.getName())
    );
  }

  @Override
  public long count() {
    Long count = namedParameterJdbcOperations.getJdbcOperations()
        .queryForObject("select count(*) from genres", Long.class);
    return count == null ? 0 : count;
  }

  private static class GenreMapper implements RowMapper<Genre> {

    @Override
    public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
      long id = resultSet.getLong("id");
      String name = resultSet.getString("name");
      return new Genre(id, name);
    }
  }
}
