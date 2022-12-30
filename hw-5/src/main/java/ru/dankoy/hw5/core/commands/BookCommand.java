package ru.dankoy.hw5.core.commands;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.dankoy.hw5.core.domain.Book;
import ru.dankoy.hw5.core.service.book.BookService;
import ru.dankoy.hw5.core.service.objectmapper.ObjectMapperService;

@RequiredArgsConstructor
@ShellComponent
public class BookCommand {

  private final BookService bookService;
  private final ObjectMapperService objectMapperService;


  @ShellMethod(key = {"book-count", "bc"}, value = "Count all book")
  public String count() {
    Long count = bookService.count();
    return objectMapperService.convertToString(count);
  }

  @ShellMethod(key = {"book-get-by-id", "bgbi"}, value = "Get book by id")
  public String getById(@ShellOption long id) {
    Book book = bookService.getById(id);
    return objectMapperService.convertToString(book);
  }


  @ShellMethod(key = {"book-get-all", "bga"}, value = "Get all book")
  public String getAll() {
    List<Book> books = bookService.getAll();
    return objectMapperService.convertToString(books);
  }


  @ShellMethod(key = {"book-insert", "bi"}, value = "Insert new book")
  public String insert(@ShellOption String bookName, @ShellOption long[] authorIds,
      @ShellOption long[] genreIds) {

    Book book = new Book(0L, bookName, new ArrayList<>(), new ArrayList<>());

    long id = bookService.insert(book, authorIds, genreIds);
    return objectMapperService.convertToString(id);
  }

  @ShellMethod(key = {"book-delete", "bd"}, value = "Delete book by id")
  public String deleteById(@ShellOption long id) {
    bookService.deleteById(id);
    return String.format("Deleted book with id - %d", id);
  }


  @ShellMethod(key = {"book-update", "bu"}, value = "Update book")
  public String update(@ShellOption long id, @ShellOption String bookName,
      @ShellOption long[] authorIds, @ShellOption long[] genreIds) {

    Book book = new Book(id, bookName, new ArrayList<>(), new ArrayList<>());
    bookService.update(book, authorIds, genreIds);
    return String.format("Updated book - %s", objectMapperService.convertToString(book));
  }

}
