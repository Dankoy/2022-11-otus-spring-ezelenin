package ru.dankoy.hw5.core.service.book;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dankoy.hw5.core.dao.book.BookDao;
import ru.dankoy.hw5.core.domain.Book;

@Transactional
@Service
@RequiredArgsConstructor
public class BookServiceJdbc implements BookService {

  private final BookDao bookDao;

  @Override
  public List<Book> getAll() {
    return bookDao.getAll();
  }

  @Override
  public Book getById(long id) {
    return bookDao.getById(id);
  }

  @Override
  public long insert(Book book) {
    return bookDao.insert(book);
  }

  @Override
  public void deleteById(long id) {
    bookDao.deleteById(id);
  }

  @Override
  public void update(Book book) {
    bookDao.update(book);
  }

  @Override
  public long count() {
    return bookDao.count();
  }
}
