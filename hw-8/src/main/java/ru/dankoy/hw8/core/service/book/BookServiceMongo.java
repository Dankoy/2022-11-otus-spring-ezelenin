package ru.dankoy.hw8.core.service.book;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dankoy.hw8.core.domain.Author;
import ru.dankoy.hw8.core.domain.Book;
import ru.dankoy.hw8.core.domain.Genre;
import ru.dankoy.hw8.core.exceptions.EntityNotFoundException;
import ru.dankoy.hw8.core.repository.book.BookRepository;
import ru.dankoy.hw8.core.service.author.AuthorService;
import ru.dankoy.hw8.core.service.genre.GenreService;

@Service
@RequiredArgsConstructor
public class BookServiceMongo implements BookService {

  private final BookRepository bookRepository;
  private final GenreService genreService;
  private final AuthorService authorService;


  public List<Book> findAll() {
    return bookRepository.findAll();
  }

  @Override
  public Optional<Book> getById(String id) {
    return bookRepository.findById(id);
  }

  @Override
  public Book insertOrUpdate(Book book, String[] authorIds, String[] genreIds) {

    List<Author> authors = getRealAuthors(authorIds);
    List<Genre> genres = getRealGenres(genreIds);

    book.getAuthors().addAll(authors);
    book.getGenres().addAll(genres);

    return bookRepository.save(book);
  }

  @Override
  public void deleteById(String id) {
    var optional = bookRepository.findById(id);
    var book = optional.orElseThrow(() -> new EntityNotFoundException(
        String.format("Entity %s has not been found with id - %d", Book.class.getName(), id)));
    bookRepository.delete(book);
  }

  @Transactional
  @Override
  public Book update(Book book, String[] authorIds, String[] genreIds) {

    var optional = bookRepository.findById(book.getId());
    var found = optional.orElseThrow(() -> new EntityNotFoundException(
        String.format("Entity %s has not been found with id - %d", Book.class.getName(),
            book.getId())));

    List<Author> authors = convertAuthorIdsToObjects(authorIds);
    List<Genre> genres = convertGenreIdsToObjects(genreIds);

    book.getAuthors().addAll(authors);
    book.getGenres().addAll(genres);

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


  private List<Genre> getRealGenres(String[] ids) {

    // получает реальные объекты жанров из бд + выбрасывает исключение, если жанр не был найден
    return Arrays.stream(ids).map(id -> {
      var optional = genreService.getById(id);
      return optional.orElseThrow(() -> new EntityNotFoundException(
          String.format("Entity %s has not been found with id - %d", Genre.class.getName(), id)));
    }).collect(Collectors.toList());

  }

  private List<Author> getRealAuthors(String[] ids) {

    // получает реальные объекты авторов из бд + выбрасывает исключение, если автор не был найден
    return Arrays.stream(ids).map(id -> {
      var optional = authorService.getById(id);
      return optional.orElseThrow(() -> new EntityNotFoundException(
          String.format("Entity %s has not been found with id - %d", Author.class.getName(), id)));
    }).collect(Collectors.toList());

  }

  private List<Genre> convertGenreIdsToObjects(String[] ids) {

    // Просто формирует объекты жанров. Для этого был создан отдельный конструктор с одним параметром - id
    // Этого для hibernate достаточно, что бы добавить или обновить книгу, но тогда что бы вернуть
    // в ответе полные данные книги - надо делать отдельный запрос в бд по id (fetch).
    return Arrays.stream(ids)
        .map(Genre::new)
        .collect(Collectors.toList());

  }

  private List<Author> convertAuthorIdsToObjects(String[] ids) {

    // Просто формирует объекты жанров. Для этого был создан отдельный конструктор с одним параметром - id
    // Этого для hibernate достаточно, что бы добавить или обновить книгу, но тогда что бы вернуть
    // в ответе полные данные книги - надо делать отдельный запрос в бд по id (fetch).
    return Arrays.stream(ids)
        .map(Author::new)
        .collect(Collectors.toList());

  }
}
