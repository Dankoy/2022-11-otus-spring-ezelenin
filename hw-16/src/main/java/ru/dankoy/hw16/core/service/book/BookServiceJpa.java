package ru.dankoy.hw16.core.service.book;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dankoy.hw16.core.domain.Book;
import ru.dankoy.hw16.core.exceptions.LibraryElement;
import ru.dankoy.hw16.core.exceptions.EntityNotFoundException;
import ru.dankoy.hw16.core.repository.book.BookRepository;

@Service
@RequiredArgsConstructor
public class BookServiceJpa implements BookService {

  private final BookRepository bookRepository;


  @Override
  public List<Book> getAll() {
    return bookRepository.findAll();
  }

  @Override
  public Optional<Book> getById(long id) {
    return bookRepository.getById(id);
  }

  @Override
  public Book insertOrUpdate(Book book) {
    return bookRepository.save(book);
  }

  @Override
  public void deleteById(long id) {
    var optional = bookRepository.getById(id);
    optional.ifPresent(bookRepository::delete);
  }

  @Transactional
  @Override
  public Book update(Book book) {

    var optional = bookRepository.getById(book.getId());
    var found = optional.orElseThrow(() -> new EntityNotFoundException(book.getId(), LibraryElement.BOOK));

    // Добавляем комментарии к обновляемой книги, иначе они будут удалены, а это нам не нужно
    if (book.getCommentaries().isEmpty()) {
      book.getCommentaries().addAll(found.getCommentaries());
    }

    return bookRepository.save(book);
  }

  @Override
  public long count() {
    return bookRepository.count();
  }

}
