package ru.dankoy.hw5.core.dao.book;

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
import ru.dankoy.hw5.core.domain.Author;
import ru.dankoy.hw5.core.domain.Book;
import ru.dankoy.hw5.core.domain.Genre;
import ru.dankoy.hw5.core.exceptions.BookDaoException;


@RequiredArgsConstructor
@Repository
public class BookDaoJdbc implements BookDao {

  private static final String AUTHOR_ID_COLUMN = "author_id";
  private static final String GENRE_ID_COLUMN = "genre_id";

  private final NamedParameterJdbcOperations namedParameterJdbcOperations;

  @Override
  public List<Book> getAll() {

    String query = "select books.id as book_id, books.name as book_name, books.author_id, "
        + "authors.name as author_name, books.genre_id, genres.name as genre_name "
        + "from books "
        + "join authors on authors.id = books.author_id "
        + "join genres on genres.id = books.genre_id";

    return namedParameterJdbcOperations.query(
        query, new BookRowMapper()
    );

  }

  @Override
  public Book getById(long id) {
    Map<String, Object> params = Collections.singletonMap("id", id);

    String query = "select books.id as book_id, books.name as book_name, books.author_id, "
        + "authors.name as author_name, books.genre_id, genres.name as genre_name "
        + "from books "
        + "join authors on authors.id = books.author_id "
        + "join genres on genres.id = books.genre_id "
        + "where books.id = :id";

    try {
      return namedParameterJdbcOperations.queryForObject(
          query, params, new BookRowMapper()
      );
    } catch (Exception e) {
      throw new BookDaoException(String.format("Book with id '%d' does not exist", id), e);
    }

  }

  @Override
  public long insert(Book book) {
    String query = "insert into books (name, author_id, genre_id) values (:name, :author_id, :genre_id)";

    MapSqlParameterSource parameters = new MapSqlParameterSource()
        .addValue("name", book.getName())
        .addValue(AUTHOR_ID_COLUMN, book.getAuthor().getId())
        .addValue(GENRE_ID_COLUMN, book.getGenre().getId());

    KeyHolder keyHolder = new GeneratedKeyHolder();
    namedParameterJdbcOperations.update(query, parameters, keyHolder);

    Optional<Number> optionalNumber = Optional.ofNullable(keyHolder.getKey());
    Number number = optionalNumber.orElseThrow(() -> new BookDaoException(
        "Expecting key holder not null, but got " + keyHolder.getKey()));
    return number.longValue();
  }

  @Override
  public void deleteById(long id) {
    String query = "delete from books where id = :id";

    Map<String, Object> params = Collections.singletonMap("id", id);
    int affected = namedParameterJdbcOperations.update(query, params);
    if (affected == 0) {
      throw new BookDaoException(
          String.format("Can't delete book. Book with id '%d' does not exist", id));
    }

  }

  @Override
  public void update(Book book) {

    Map<String, Object> params = Map.of(
        "id", book.getId(), "name", book.getName(),
        AUTHOR_ID_COLUMN, book.getAuthor().getId(),
        GENRE_ID_COLUMN, book.getGenre().getId()
    );

    String query = "update books set name = :name, "
        + "author_id = :author_id, "
        + "genre_id = :genre_id "
        + "where id = :id";

    namedParameterJdbcOperations.update(query, params);
  }

  @Override
  public long count() {
    Long count = namedParameterJdbcOperations.getJdbcOperations()
        .queryForObject("select count(*) from books", Long.class);
    return count == null ? 0 : count;
  }


  private static class BookRowMapper implements RowMapper<Book> {

    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {

      Long bookId = rs.getLong("book_id");
      String bookName = rs.getString("book_name");
      Long authorId = rs.getLong(AUTHOR_ID_COLUMN);
      String authorName = rs.getString("author_name");
      Long genreId = rs.getLong(GENRE_ID_COLUMN);
      String genreName = rs.getString("genre_name");

      Author author = new Author(authorId, authorName);
      Genre genre = new Genre(genreId, genreName);

      return new Book(bookId, bookName, author, genre);
    }

  }
}