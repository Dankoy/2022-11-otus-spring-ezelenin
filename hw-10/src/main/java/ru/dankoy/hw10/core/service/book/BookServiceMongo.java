package ru.dankoy.hw10.core.service.book;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dankoy.hw10.core.domain.Author;
import ru.dankoy.hw10.core.domain.Book;
import ru.dankoy.hw10.core.domain.Commentary;
import ru.dankoy.hw10.core.domain.Genre;
import ru.dankoy.hw10.core.exceptions.BookServiceException;
import ru.dankoy.hw10.core.exceptions.Entity;
import ru.dankoy.hw10.core.exceptions.EntityNotFoundException;
import ru.dankoy.hw10.core.repository.book.BookRepository;
import ru.dankoy.hw10.core.service.commentary.CommentaryService;

@Service
@RequiredArgsConstructor
public class BookServiceMongo implements BookService {

  private final BookRepository bookRepository;
  private final CommentaryService commentaryService;


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
    var optional = bookRepository.findById(id);
    var book = optional.orElseThrow(() -> new EntityNotFoundException(id, Entity.BOOK));

    List<Commentary> commentaries = commentaryService.getAllByBookId(id);

    if (!commentaries.isEmpty()) {
      throw new BookServiceException(
          "Book contains commentaries. First delete all of them, then retry.");
    }

    bookRepository.delete(book);
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
