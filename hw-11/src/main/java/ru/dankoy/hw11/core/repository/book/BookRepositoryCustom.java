package ru.dankoy.hw11.core.repository.book;

import com.mongodb.client.result.DeleteResult;
import java.util.List;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.dankoy.hw11.core.domain.Book;
import ru.dankoy.hw11.core.domain.Genre;

public interface BookRepositoryCustom {

  Flux<Genre> getAllGenresByBookId(String bookId);

  Mono<Book> saveAndCheckAuthors(Book book);

  Mono<DeleteResult> deleteByBookId(Mono<String> bookId);
}
