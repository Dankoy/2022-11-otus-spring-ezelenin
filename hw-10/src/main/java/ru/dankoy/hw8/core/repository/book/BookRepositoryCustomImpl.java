package ru.dankoy.hw8.core.repository.book;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.unwind;

import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import ru.dankoy.hw8.core.domain.Author;
import ru.dankoy.hw8.core.domain.Book;
import ru.dankoy.hw8.core.domain.Genre;
import ru.dankoy.hw8.core.exceptions.EntityNotFoundException;


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

  @Override
  public Book saveAndCheckAuthors(Book book) {

    Set<Author> authors = book.getAuthors();

    // проверяем, что авторы присутствуют в коллекции авторов
    authors.forEach(author -> {
      List<Author> found = mongoTemplate.find(new Query()
              .addCriteria(Criteria
                  .where("_id").is(author.getId())),
          Author.class);

      if (found.isEmpty()) {
        throw new EntityNotFoundException(author.getId());
      }
    });

    return mongoTemplate.save(book, "books");
  }


}
