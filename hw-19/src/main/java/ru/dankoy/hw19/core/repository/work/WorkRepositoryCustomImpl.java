package ru.dankoy.hw19.core.repository.work;

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
import ru.dankoy.hw19.core.domain.Author;
import ru.dankoy.hw19.core.domain.Work;
import ru.dankoy.hw19.core.domain.Genre;
import ru.dankoy.hw19.core.exceptions.Entity;
import ru.dankoy.hw19.core.exceptions.EntityNotFoundException;
import ru.dankoy.hw19.core.repository.commentary.CommentaryRepository;


@RequiredArgsConstructor
public class WorkRepositoryCustomImpl implements WorkRepositoryCustom {

  private final MongoTemplate mongoTemplate;

  private final CommentaryRepository commentaryRepository;

  @Override
  public List<Genre> getAllGenresByBookId(String bookId) {
    var aggregation = newAggregation(
        match(Criteria.where("id").is(bookId))
        , unwind("genres")
        , project().andExclude("_id")
            .and("genres.name").as("name")
    );

    return mongoTemplate.aggregate(aggregation, Work.class, Genre.class).getMappedResults();
  }

  @Override
  public Work saveAndCheckAuthors(Work work) {

    Set<Author> authors = work.getAuthors();

    // проверяем, что авторы присутствуют в коллекции авторов
    authors.forEach(author -> {
      List<Author> found = mongoTemplate.find(new Query()
              .addCriteria(Criteria
                  .where("_id").is(author.getId())),
          Author.class);

      if (found.isEmpty()) {
        throw new EntityNotFoundException(author.getId(), Entity.AUTHOR);
      }
    });

    return mongoTemplate.save(work, "books");
  }


  @Override
  public void deleteByBookId(String bookId) {

    var query = Query.query(Criteria.where("_id").is(bookId));
    commentaryRepository.deleteCommentariesByWorkId(bookId);
    mongoTemplate.remove(query, Work.class);

  }

}
