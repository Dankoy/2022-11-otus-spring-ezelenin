package ru.dankoy.hw11.core.repository.book;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.unwind;

import com.mongodb.client.result.DeleteResult;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.dankoy.hw11.core.domain.Author;
import ru.dankoy.hw11.core.domain.Book;
import ru.dankoy.hw11.core.domain.Genre;
import ru.dankoy.hw11.core.exceptions.Entity;
import ru.dankoy.hw11.core.exceptions.EntityNotFoundException;
import ru.dankoy.hw11.core.repository.author.AuthorRepository;
import ru.dankoy.hw11.core.repository.commentary.CommentaryRepository;


@RequiredArgsConstructor
public class BookRepositoryCustomImpl implements BookRepositoryCustom {

  private final ReactiveMongoTemplate mongoTemplate;

  private final CommentaryRepository commentaryRepository;
  private final AuthorRepository authorRepository;

  @Override
  public Flux<Genre> getAllGenresByBookId(String bookId) {
    var aggregation = newAggregation(
        match(Criteria.where("id").is(bookId))
        , unwind("genres")
        , project().andExclude("_id")
            .and("genres.name").as("name")
    );

    return mongoTemplate.aggregate(aggregation, Book.class, Genre.class);
  }

  @Override
  public Mono<Book> saveAndCheckAuthors(Book book) {

    Set<Author> authors = book.getAuthors();

    Set<String> ids = authors.stream().map(Author::getId).collect(Collectors.toSet());

    var query = new Query();

    for (Author author : authors) {
      query.addCriteria(Criteria
          .where("_id").is(author.getId()));
    }

    // проверяем, что авторы присутствуют в коллекции авторов

    return authorRepository.findAllByAuthorId(ids)
        .switchIfEmpty(Mono.error(new EntityNotFoundException(ids.toString(), Entity.AUTHOR)))
        .then(mongoTemplate.save(book, "books"));

  }


  @Override
  public Mono<DeleteResult> deleteByBookId(Mono<String> bookId) {

    var query = Query.query(Criteria.where("_id").is(bookId));

    return commentaryRepository
        .deleteCommentariesByBookId(bookId)
        .then(mongoTemplate.remove(query, Book.class));

  }

}
