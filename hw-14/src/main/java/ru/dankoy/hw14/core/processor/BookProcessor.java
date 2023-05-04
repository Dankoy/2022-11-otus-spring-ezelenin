package ru.dankoy.hw14.core.processor;

import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import ru.dankoy.hw14.core.domain.mongodb.Author;
import ru.dankoy.hw14.core.domain.mongodb.Book;
import ru.dankoy.hw14.core.domain.mongodb.Genre;

@Component
public class BookProcessor implements
    ItemProcessor<ru.dankoy.hw14.core.domain.sql.Book, Book> {

  @Override
  public Book process(ru.dankoy.hw14.core.domain.sql.Book item) {

    Set<Genre> nosqlGenres = item.getGenres().stream()
        .map(g -> new Genre(g.getName()))
        .collect(Collectors.toSet());

    Set<Author> nosqlAuthors = item.getAuthors().stream()
        .map(a -> new Author(Long.toString(a.getId()), a.getName()))
        .collect(Collectors.toSet());

    return new Book(Long.toString(item.getId()), item.getName(), nosqlAuthors, nosqlGenres);
  }
}
