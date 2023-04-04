package ru.dankoy.hw16.core.repository.commentary;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import ru.dankoy.hw16.core.domain.Book;

@DisplayName("Test CommentaryRepository ")
@DataMongoTest
class CommentaryRepositoryTest {


  @Autowired
  private CommentaryRepository commentaryRepository;

  @Autowired
  private MongoTemplate mongoTemplate;


  @DisplayName(" should deleteByBookId all commentaries by book id")
  @Test
  void deleteDeleteAllCommentariesByBookId() {

    var book1 = mongoTemplate.find(
            new Query().addCriteria(Criteria.where("name").is("book1")),
            Book.class)
        .get(0);

    var commentariesBeforeDelete = commentaryRepository.findAllByBookId(book1.getId());
    assertThat(commentariesBeforeDelete).hasSize(3);

    commentaryRepository.deleteCommentariesByBookId(book1.getId());

    var commentariesAfterDelete = commentaryRepository.findAllByBookId(book1.getId());
    assertThat(commentariesAfterDelete).isEmpty();


  }


}
