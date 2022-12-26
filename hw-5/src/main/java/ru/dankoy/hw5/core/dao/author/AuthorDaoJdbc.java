package ru.dankoy.hw5.core.dao.author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.dankoy.hw5.core.domain.Author;
import ru.dankoy.hw5.core.exceptions.AuthorDaoException;


@Repository
@AllArgsConstructor
public class AuthorDaoJdbc implements AuthorDao {

  private final NamedParameterJdbcOperations namedParameterJdbcOperations;

  @Override
  public List<Author> getAll() {
    return namedParameterJdbcOperations.query("select id, name from authors", new AuthorMapper());
  }

  @Override
  public Author getById(long id) {
    Map<String, Object> params = Collections.singletonMap("id", id);
    try {
      return namedParameterJdbcOperations.queryForObject(
          "select id, name from authors where id = :id", params, new AuthorMapper()
      );
    } catch (Exception e) {
      throw new AuthorDaoException(String.format("Author with id '%d' does not exist", id), e);
    }

  }

  @Override
  public long insert(String authorName) {
    MapSqlParameterSource parameters = new MapSqlParameterSource()
        .addValue("name", authorName);
    KeyHolder keyHolder = new GeneratedKeyHolder();
    namedParameterJdbcOperations
        .update("insert into authors (name) values (:name)", parameters, keyHolder);

    Optional<Number> optionalNumber = Optional.ofNullable(keyHolder.getKey());
    Number number = optionalNumber.orElseThrow(() -> new AuthorDaoException(
        "Expecting key holder not null, but got " + keyHolder.getKey()));
    return number.longValue();

  }

  @Override
  public void deleteById(long id) {
    Map<String, Object> params = Collections.singletonMap("id", id);
    int affected = namedParameterJdbcOperations.update(
        "delete from authors where id = :id", params
    );
    if (affected == 0) {
      throw new AuthorDaoException(
          String.format("Can't delete author. Author with id '%d' does not exist", id));
    }
  }

  @Override
  public void update(Author author) {
    namedParameterJdbcOperations.update(
        "update authors set name = :name where id = :id",
        Map.of("id", author.getId(), "name", author.getName())
    );
  }

  @Override
  public long count() {
    Long count = namedParameterJdbcOperations.getJdbcOperations()
        .queryForObject("select count(*) from author", Long.class);
    return count == null ? 0 : count;
  }

  private static class AuthorMapper implements RowMapper<Author> {

    @Override
    public Author mapRow(ResultSet resultSet, int i) throws SQLException {
      long id = resultSet.getLong("id");
      String name = resultSet.getString("name");
      return new Author(id, name);
    }
  }
}
