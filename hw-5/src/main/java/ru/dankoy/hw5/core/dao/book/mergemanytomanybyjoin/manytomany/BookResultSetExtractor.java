package ru.dankoy.hw5.core.dao.book.mergemanytomanybyjoin.manytomany;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import ru.dankoy.hw5.core.domain.Author;
import ru.dankoy.hw5.core.domain.Book;
import ru.dankoy.hw5.core.domain.Genre;

public class BookResultSetExtractor implements ResultSetExtractor<Map<Long, Book>> {

  @Override
  public Map<Long, Book> extractData(ResultSet rs) throws SQLException, DataAccessException {
    Map<Long, Book> books = new HashMap<>();

    Map<Long, Genre> genres = new HashMap<>();
    Map<Long, Author> authors = new HashMap<>();

    while (rs.next()) {

      long id = rs.getLong("b_id");
      Book book = books.get(id);
      if (book == null) {
        String bookName = rs.getString("book_name");
        book = new Book(id, bookName, new ArrayList<>(), new ArrayList<>());
        books.put(book.getId(), book);
      }

      long authorId = rs.getLong("ba_id");
      Author author = authors.get(authorId);
      if (author == null) {
        author = new Author(authorId, rs.getString("author_name"));
        authors.put(author.getId(), author);
      }

      long genreId = rs.getLong("bg_id");
      Genre genre = genres.get(genreId);
      if (genre == null) {
        genre = new Genre(genreId, rs.getString("genre_name"));
        genres.put(genre.getId(), genre);
      }

      if (!book.getAuthors().contains(author)) {
        book.getAuthors().add(author);
      }
      if (!book.getGenres().contains(genre)) {
        book.getGenres().add(genre);
      }

    }
    return books;
  }

}
