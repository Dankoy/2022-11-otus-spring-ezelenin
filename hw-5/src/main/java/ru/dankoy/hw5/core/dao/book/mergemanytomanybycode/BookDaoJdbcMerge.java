package ru.dankoy.hw5.core.dao.book.mergemanytomanybycode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
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
import ru.dankoy.hw5.core.dao.book.mergemanytomanybycode.manytomany.BookAuthorRelation;
import ru.dankoy.hw5.core.dao.book.mergemanytomanybycode.manytomany.BookGenreRelation;
import ru.dankoy.hw5.core.dao.book.mergemanytomanybycode.manytomany.BookResultSetExtractor;
import ru.dankoy.hw5.core.dao.genre.GenreDao;
import ru.dankoy.hw5.core.domain.Author;
import ru.dankoy.hw5.core.domain.Book;
import ru.dankoy.hw5.core.domain.Genre;
import ru.dankoy.hw5.core.exceptions.BookDaoException;


/**
 * @author turtality
 * <p>
 * Dao works with many-to-many in code by merge methods
 */
@RequiredArgsConstructor
@Repository
@ConditionalOnProperty(value = "book.dao.join", havingValue = "false")
public class BookDaoJdbcMerge implements BookDao {

  private final NamedParameterJdbcOperations namedParameterJdbcOperations;

  private final AuthorDao authorDao;
  private final GenreDao genreDao;

  @Override
  public List<Book> getAll() {

    List<Genre> genres = genreDao.getAll();
    List<Author> authors = authorDao.getAll();

    List<BookAuthorRelation> bookAuthorRelations = getAllBookAuthorRelations();
    List<BookGenreRelation> bookGenreRelations = getAllBookGenreRelations();

    String query = "select id, name from books";

    Map<Long, Book> books = namedParameterJdbcOperations.query(
        query, new BookResultSetExtractor()
    );

    mergeBookInfo(books, authors, genres, bookAuthorRelations, bookGenreRelations);

    return new ArrayList<>(Objects.requireNonNull(books).values());

  }

  @Override
  public Book getById(long id) {

    List<Genre> genres = genreDao.getAll();
    List<Author> authors = authorDao.getAll();

    List<BookAuthorRelation> bookAuthorRelations = getAllBookAuthorRelations();
    List<BookGenreRelation> bookGenreRelations = getAllBookGenreRelations();

    Map<String, Object> params = Collections.singletonMap("id", id);

    String query = "select id, name from books where id = :id";

    Map<Long, Book> books = namedParameterJdbcOperations.query(
        query, params, new BookResultSetExtractor());

    mergeBookInfo(books, authors, genres, bookAuthorRelations, bookGenreRelations);

    if (Objects.requireNonNull(books).size() < 1) {
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


  private List<BookAuthorRelation> getAllBookAuthorRelations() {
    return namedParameterJdbcOperations.query(
        "select book_id, author_id from books_authors ba order by book_id, author_id",
        (rs, i) -> new BookAuthorRelation(rs.getLong(1), rs.getLong(2)));
  }

  private List<BookGenreRelation> getAllBookGenreRelations() {
    return namedParameterJdbcOperations.query(
        "select book_id, genre_id from books_genres bg order by book_id, genre_id",
        (rs, i) -> new BookGenreRelation(rs.getLong(1), rs.getLong(2)));
  }

  private List<BookAuthorRelation> getBookAuthorRelationsByBookId(long bookId) {
    Map<String, Object> params = Map.of(
        "book_id", bookId
    );

    return namedParameterJdbcOperations.query(
        "select book_id, author_id from books_authors ba where book_id = :book_id order by book_id, author_id",
        params,
        (rs, i) -> new BookAuthorRelation(rs.getLong(1), rs.getLong(2)));
  }

  private List<BookGenreRelation> getBookGenreRelationsByBookId(long bookId) {
    Map<String, Object> params = Map.of(
        "book_id", bookId
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

  private void mergeBookInfo(Map<Long, Book> books, List<Author> authors,
      List<Genre> genres, List<BookAuthorRelation> bookAuthorRelations,
      List<BookGenreRelation> bookGenreRelations) {
    Map<Long, Author> authorMap = authors.stream().collect(
        Collectors.toMap(Author::getId, Function.identity()));
    Map<Long, Genre> genreMap = genres.stream().collect(
        Collectors.toMap(Genre::getId, Function.identity()));

    mergeAuthorsToBook(books, authorMap, bookAuthorRelations);
    mergeGenresToBook(books, genreMap, bookGenreRelations);

  }

  private void mergeAuthorsToBook(Map<Long, Book> books, Map<Long, Author> authorMap,
      List<BookAuthorRelation> bookAuthorRelations) {

    bookAuthorRelations.forEach(relation -> {
      if (books.containsKey(relation.getBookId()) && authorMap.containsKey(
          relation.getAuthorId())) {
        books.get(relation.getBookId()).getAuthors().add(authorMap.get(relation.getAuthorId()));
      }
    });

  }

  private void mergeGenresToBook(Map<Long, Book> books, Map<Long, Genre> genreMap,
      List<BookGenreRelation> bookGenreRelations) {

    bookGenreRelations.forEach(relation -> {
      if (books.containsKey(relation.getBookId()) && genreMap.containsKey(
          relation.getGenreId())) {
        books.get(relation.getBookId()).getGenres().add(genreMap.get(relation.getGenreId()));
      }
    });

  }

}
