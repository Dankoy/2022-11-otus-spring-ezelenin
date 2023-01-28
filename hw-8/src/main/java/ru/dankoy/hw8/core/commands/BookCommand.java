package ru.dankoy.hw8.core.commands;

import java.util.HashSet;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.dankoy.hw8.core.domain.Book;
import ru.dankoy.hw8.core.dto.mapper.BookMapper;
import ru.dankoy.hw8.core.service.book.BookService;
import ru.dankoy.hw8.core.service.objectmapper.ObjectMapperService;
import ru.dankoy.hw8.core.service.utils.OptionalChecker;

@RequiredArgsConstructor
@ShellComponent
public class BookCommand {

  private final BookService bookService;
  private final ObjectMapperService objectMapperService;
  private final BookMapper bookMapper;
  private final OptionalChecker optionalChecker;


  @ShellMethod(key = {"book-count", "bc"}, value = "Count all book")
  public String count() {
    var count = bookService.count();
    return objectMapperService.convertToString(count);
  }

  @ShellMethod(key = {"book-get-by-id", "bgbi"}, value = "Get book by id")
  public String getById(@ShellOption String id) {
    var optional = bookService.getById(id);

    var book = optionalChecker.getFromOptionalOrThrowException(Book.class, optional, id);

    var bookDto = bookMapper.toDTOWithoutCommentaries(book);

    return objectMapperService.convertToString(bookDto);
  }


  @ShellMethod(key = {"book-get-all", "bga"}, value = "Get all book")
  public String getAll() {
    var books = bookService.findAll();

    var booksDto = books.stream().map(bookMapper::toDTOWithoutCommentaries)
        .collect(Collectors.toList());

    return objectMapperService.convertToString(booksDto);
  }


  @ShellMethod(key = {"book-insert", "bi"}, value = "Insert new book")
  public String insert(@ShellOption String bookName, @ShellOption String[] authorIds,
      @ShellOption String[] genreNames) {

    var book = new Book(null, bookName, new HashSet<>(), new HashSet<>());

    var created = bookService.insertOrUpdate(book, authorIds, genreNames);

    return String.format("Created new book with id - %s", created.getId());
  }

  @ShellMethod(key = {"book-delete", "bd"}, value = "Delete book by id")
  public String deleteById(@ShellOption String id) {
    bookService.deleteById(id);
    return String.format("Deleted book with id - %s", id);
  }


  @ShellMethod(key = {"book-update", "bu"}, value = "Update book")
  public String update(@ShellOption String id, @ShellOption String bookName,
      @ShellOption String[] authorIds, @ShellOption String[] genreNames) {

    var optional = bookService.getById(id);
    var found = optionalChecker.getFromOptionalOrThrowException(Book.class, optional, id);

    var book = new Book(found.getId(), bookName, found.getAuthors(), found.getGenres());
    var updated = bookService.update(book, authorIds, genreNames);
    var booksDto = bookMapper.toDTOWithoutCommentaries(updated);

    return String.format("Updated book - %s", objectMapperService.convertToString(booksDto));
  }

}
