package ru.dankoy.hw5.core.dao.book.mergemanytomanybycode.manytomany;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import ru.dankoy.hw5.core.domain.Book;

public class BookResultSetExtractor implements ResultSetExtractor<Map<Long, Book>> {

  @Override
  public Map<Long, Book> extractData(ResultSet rs) throws SQLException, DataAccessException {
    Map<Long, Book> books = new HashMap<>();
    while (rs.next()) {
      long id = rs.getLong("id");
      Book book = books.get(id);
      if (book == null) {
        book = new Book(id, rs.getString("name"), new ArrayList<>(), new ArrayList<>());
        books.put(book.getId(), book);
      }
    }
    return books;
  }
}
