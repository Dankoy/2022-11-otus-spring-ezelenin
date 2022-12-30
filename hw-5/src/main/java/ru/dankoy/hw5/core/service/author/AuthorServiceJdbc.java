package ru.dankoy.hw5.core.service.author;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dankoy.hw5.core.dao.author.AuthorDao;
import ru.dankoy.hw5.core.domain.Author;

@Service
@RequiredArgsConstructor
public class AuthorServiceJdbc implements AuthorService {

  private final AuthorDao authorDao;

  @Override
  public List<Author> getAll() {
    return authorDao.getAll();
  }

  @Override
  public Author getById(long id) {
    return authorDao.getById(id);
  }

  @Override
  public long insert(String authorName) {
    return authorDao.insert(authorName);
  }

  @Override
  public void deleteById(long id) {
    authorDao.getById(id);
    authorDao.deleteById(id);
  }

  @Override
  public void update(Author author) {
    authorDao.getById(author.getId());
    authorDao.update(author);
  }

  @Override
  public long count() {
    return authorDao.count();
  }
}
