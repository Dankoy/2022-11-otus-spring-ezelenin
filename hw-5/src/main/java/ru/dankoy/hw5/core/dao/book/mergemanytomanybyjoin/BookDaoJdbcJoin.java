package ru.dankoy.hw5.core.dao.book.mergemanytomanybyjoin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.dankoy.hw5.core.dao.author.AuthorDao;
import ru.dankoy.hw5.core.dao.book.BookDao;
import ru.dankoy.hw5.core.dao.book.mergemanytomanybyjoin.manytomany.BookAuthorRelation;
import ru.dankoy.hw5.core.dao.book.mergemanytomanybyjoin.manytomany.BookGenreRelation;
import ru.dankoy.hw5.core.dao.book.mergemanytomanybyjoin.manytomany.BookResultSetExtractor;
import ru.dankoy.hw5.core.dao.genre.GenreDao;
import ru.dankoy.hw5.core.domain.Author;
import ru.dankoy.hw5.core.domain.Book;
import ru.dankoy.hw5.core.domain.Genre;
import ru.dankoy.hw5.core.exceptions.BookDaoException;


/**
 * @author turtality
 * <p>
 * Dao works with many-to-many by sql join tables and all magic is in
 * {@link BookResultSetExtractor}
 */
@RequiredArgsConstructor
@Repository
@ConditionalOnProperty(value = "book.dao.join", havingValue = "true", matchIfMissing = true)
public class BookDaoJdbcJoin implements BookDao {

  private static final String BOOK_ID_COLUMN = "book_id";

  private final NamedParameterJdbcOperations namedParameterJdbcOperations;

  private final AuthorDao authorDao;
  private final GenreDao genreDao;

  @Override
  public List<Book> getAll() {

    String query = "select books.id as b_id, "
        + "books.name as book_name, "
        + "books_genres.genre_id   as bg_id, "
        + "genres.name             as genre_name, "
        + "books_authors.author_id as ba_id, "
        + "authors.name            as author_name "
        + "from books "
        + "join books_genres on books.id = books_genres.book_id "
        + "join books_authors on books.id = books_authors.book_id "
        + "inner join genres on books_genres.genre_id = genres.id "
        + "inner join authors on books_authors.author_id = authors.id ";

    Map<Long, Book> books = namedParameterJdbcOperations.query(
        query, new BookResultSetExtractor()
    );

    return new ArrayList<>(Objects.requireNonNull(books).values());

  }

  @Override
  public Book getById(long id) {

    Map<String, Object> params = Collections.singletonMap(BOOK_ID_COLUMN, id);

    String query = "select books.id as b_id, "
        + "books.name as book_name, "
        + "books_genres.genre_id   as bg_id, "
        + "genres.name             as genre_name, "
        + "books_authors.author_id as ba_id, "
        + "authors.name            as author_name "
        + "from books "
        + "join books_genres on books.id = books_genres.book_id "
        + "join books_authors on books.id = books_authors.book_id "
        + "inner join genres on books_genres.genre_id = genres.id "
        + "inner join authors on books_authors.author_id = authors.id "
        + "where books.id = :book_id";

    Map<Long, Book> books = namedParameterJdbcOperations.query(
        query, params, new BookResultSetExtractor()
    );

    if (Objects.requireNonNull(books).isEmpty() || Objects.requireNonNull(books).size() > 1) {
      throw new BookDaoException(String.format("Book with id '%d' does not exist", id));
    } else {
      return books.get(id);
    }

  }

  @Override
  public long insert(Book book, long[] authorIds, long[] genreIds) {

    String queryBook = "insert into books (name) values (:name)";

    MapSqlParameterSource parameters = new MapSqlParameterSource()
        .addValue("name", book.getName());

    KeyHolder keyHolder = new GeneratedKeyHolder();
    namedParameterJdbcOperations.update(queryBook, parameters, keyHolder);

    Optional<Number> optionalNumber = Optional.ofNullable(keyHolder.getKey());
    Number number = optionalNumber.orElseThrow(() -> new BookDaoException(
        "Expecting key holder not null, but got " + keyHolder.getKey()));

    long bookId = number.longValue();

    insertToBookAuthorRelation(bookId, authorIds);
    insertToBookGenreRelation(bookId, genreIds);

    return bookId;
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
  public void update(Book book, long[] authorIds, long[] genreIds) {

    Map<String, Object> params = Map.of(
        "id", book.getId(), "name", book.getName()
    );

    String query = "update books set name = :name where id = :id";

    namedParameterJdbcOperations.update(query, params);

    List<BookAuthorRelation> bookAuthorRelations = getBookAuthorRelationsByBookId(book.getId());
    List<BookGenreRelation> bookGenreRelations = getBookGenreRelationsByBookId(book.getId());

    deleteAllBookAuthorRelationsByBookId(bookAuthorRelations);
    deleteAllBookGenreRelationsByBookId(bookGenreRelations);

    insertToBookAuthorRelation(book.getId(), authorIds);
    insertToBookGenreRelation(book.getId(), genreIds);

    List<Author> authors = getAllAuthorsByIds(authorIds);
    List<Genre> genres = getAllGenresByIds(genreIds);

    book.getAuthors().addAll(authors);
    book.getGenres().addAll(genres);

  }

  @Override
  public long count() {
    Long count = namedParameterJdbcOperations.getJdbcOperations()
        .queryForObject("select count(*) from books", Long.class);
    return count == null ? 0 : count;
  }

  private List<BookAuthorRelation> getBookAuthorRelationsByBookId(long bookId) {
    Map<String, Object> params = Map.of(
        BOOK_ID_COLUMN, bookId
    );

    return namedParameterJdbcOperations.query(
        "select book_id, author_id from books_authors ba where book_id = :book_id order by book_id, author_id",
        params,
        (rs, i) -> new BookAuthorRelation(rs.getLong(1), rs.getLong(2)));
  }

  private List<BookGenreRelation> getBookGenreRelationsByBookId(long bookId) {
    Map<String, Object> params = Map.of(
        BOOK_ID_COLUMN, bookId
    );

    return namedParameterJdbcOperations.query(
        "select book_id, genre_id from books_genres bg where book_id = :book_id order by book_id, genre_id",
        params,
        (rs, i) -> new BookGenreRelation(rs.getLong(1), rs.getLong(2)));
  }

  private List<Author> getAllAuthorsByIds(long[] authorIds) {

    List<Author> authors = new ArrayList<>();

    for (long id : authorIds) {

      Author author = authorDao.getById(id);
      authors.add(author);
    }

    return authors;
  }

  private List<Genre> getAllGenresByIds(long[] genreIds) {

    List<Genre> genres = new ArrayList<>();

    for (long id : genreIds) {

      Genre genre = genreDao.getById(id);
      genres.add(genre);
    }

    return genres;
  }

  private void insertToBookAuthorRelation(long bookId, long[] authorIds) {

    List<BookAuthorRelation> bookAuthorRelations = Arrays.stream(authorIds)
        .mapToObj(id -> new BookAuthorRelation(bookId, id))
        .collect(Collectors.toList());

    SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(bookAuthorRelations);

    String query = "insert into books_authors (book_id, author_id) values (:bookId, :authorId)";

    namedParameterJdbcOperations.batchUpdate(query, batch);

  }

  private void insertToBookGenreRelation(long bookId, long[] genreIds) {

    List<BookGenreRelation> bookGenreRelations = Arrays.stream(genreIds)
        .mapToObj(id -> new BookGenreRelation(bookId, id))
        .collect(Collectors.toList());

    SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(bookGenreRelations);

    String query = "insert into books_genres (book_id, genre_id) values (:bookId, :genreId)";

    namedParameterJdbcOperations.batchUpdate(query, batch);

  }

  private void deleteAllBookAuthorRelationsByBookId(List<BookAuthorRelation> bookAuthorRelations) {

    SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(bookAuthorRelations);

    String query = "delete from books_authors where book_id = :bookId";

    namedParameterJdbcOperations.batchUpdate(query, batch);

  }

  private void deleteAllBookGenreRelationsByBookId(List<BookGenreRelation> bookGenreRelations) {

    SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(bookGenreRelations);

    String query = "delete from books_genres where book_id = :bookId";

    namedParameterJdbcOperations.batchUpdate(query, batch);

  }

}
