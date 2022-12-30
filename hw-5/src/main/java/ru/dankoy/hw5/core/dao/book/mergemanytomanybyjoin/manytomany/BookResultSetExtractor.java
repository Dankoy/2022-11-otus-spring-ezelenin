package ru.dankoy.hw5.core.dao.book.mergemanytomanybyjoin.manytomany;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import ru.dankoy.hw5.core.domain.Author;
import ru.dankoy.hw5.core.domain.Book;
import ru.dankoy.hw5.core.domain.Genre;

public class BookResultSetExtractor implements ResultSetExtractor<List<Book>> {

  @Override
  public List<Book> extractData(ResultSet rs) throws SQLException, DataAccessException {
    Map<Long, Book> books = new HashMap<>();

    while (rs.next()) {

      long id = rs.getLong("bid");
      Book book = books.get(id);

      if (book == null) {
        String bookName = rs.getString("book_name");
        book = new Book(id, bookName, new ArrayList<>(), new ArrayList<>());
        books.put(book.getId(), book);
      }

      Genre genre = new Genre(rs.getLong("genre_id"), rs.getString("genre_name"));
      Author author = new Author(rs.getLong("author_id"), rs.getString("author_name"));

      book.getGenres().add(genre);
      book.getAuthors().add(author);
    }
    return new ArrayList<>(books.values());
  }
}
