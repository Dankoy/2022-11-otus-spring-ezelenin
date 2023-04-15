package ru.dankoy.hw17.core.service.book;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dankoy.hw17.core.domain.Author;
import ru.dankoy.hw17.core.domain.Book;
import ru.dankoy.hw17.core.domain.Genre;
import ru.dankoy.hw17.core.repository.book.BookRepository;

@Service
@RequiredArgsConstructor
public class BookServiceMongo implements BookService {

  private final BookRepository bookRepository;


  @Override
  public List<Book> findAllByGenreName(Genre genre) {

    return bookRepository.findBookByGenres(genre.getName());

  }

  @Override
  public List<Book> findAllByAuthorId(Author author) {

    return bookRepository.findBookByAuthorsId(author.getId());

  }

  @Override
  public List<Book> findAll() {
    return bookRepository.findAll();
  }

  @Override
  public Optional<Book> getById(String id) {
    return bookRepository.findById(id);
  }

  @Override
  public Book insertOrUpdate(Book book) {

    return bookRepository.saveAndCheckAuthors(book);
  }

  @Override
  public void deleteById(String id) {
    bookRepository.deleteByBookId(id);
  }

  @Override
  public long count() {
    return bookRepository.count();
  }


  @Override
  public void updateMultiple(List<Book> books) {
    bookRepository.saveAll(books);
  }

}
