package ru.dankoy.hw8.core.service.genre;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dankoy.hw8.core.domain.Book;
import ru.dankoy.hw8.core.domain.Genre;
import ru.dankoy.hw8.core.service.book.BookService;

@Service
@RequiredArgsConstructor
public class GenreServiceMongo implements GenreService {

  private final BookService bookService;

  @Override
  public void update(Genre oldGenre, Genre newGenre) {

    List<Book> books = bookService.findAllByGenreName(oldGenre);

    books.forEach(b -> {
      b.getGenres().remove(oldGenre);
      b.getGenres().add(newGenre);
    });

    bookService.updateMultiple(books);

  }

  @Override
  public void delete(Genre genre) {

    List<Book> books = bookService.findAllByGenreName(genre);

    books.forEach(b -> b.getGenres().remove(genre));

    bookService.updateMultiple(books);

  }

}
