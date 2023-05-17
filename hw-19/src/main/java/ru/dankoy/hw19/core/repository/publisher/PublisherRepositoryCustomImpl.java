package ru.dankoy.hw19.core.repository.publisher;

import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import ru.dankoy.hw19.core.domain.Edition;
import ru.dankoy.hw19.core.domain.Publisher;
import ru.dankoy.hw19.core.exceptions.Hw5RootException;


@RequiredArgsConstructor
public class PublisherRepositoryCustomImpl implements PublisherRepositoryCustom {

  private final MongoTemplate mongoTemplate;
  
  @Override
  public void deleteAndCheckEditions(Publisher publisher) {

    var query = new Query()
        .addCriteria(
            Criteria
                .where("publisher")
                .is(new ObjectId(publisher.getId()))
        );

    var editions = mongoTemplate.find(query, Edition.class);
    var editionsIds = editions.stream().map(Edition::getId).collect(Collectors.toSet());

    if (!editions.isEmpty()) {
      throw new Hw5RootException(
          String.format("Can't delete publisher, because editions require it. '%s'", editionsIds)
      );
    }

    mongoTemplate.remove(publisher, "publishers");

  }
}
