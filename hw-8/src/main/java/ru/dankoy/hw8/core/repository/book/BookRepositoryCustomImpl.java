package ru.dankoy.hw8.core.repository.book;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.unwind;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import ru.dankoy.hw8.core.domain.Book;
import ru.dankoy.hw8.core.domain.Genre;


@RequiredArgsConstructor
public class BookRepositoryCustomImpl implements BookRepositoryCustom {

  private final MongoTemplate mongoTemplate;

  @Override
  public List<Genre> getAllGenresByBookId(String bookId) {
    var aggregation = newAggregation(
        match(Criteria.where("id").is(bookId))
        , unwind("genres")
        , project().andExclude("_id")
            .and("genres.name").as("name")
    );

    return mongoTemplate.aggregate(aggregation, Book.class, Genre.class).getMappedResults();
  }
}
