package ru.dankoy.hw19.core.repository.edition;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import ru.dankoy.hw19.core.domain.Edition;
import ru.dankoy.hw19.core.domain.Note;
import ru.dankoy.hw19.core.domain.Shelf;
import ru.dankoy.hw19.core.domain.Work;

@RequiredArgsConstructor
public class EditionRepositoryCustomImpl implements EditionRepositoryCustom {

  private final MongoTemplate mongoTemplate;

  private static final String EDITIONS_FIELD_NAME = "editions";

  @Override
  public void deleteAndCheckNotesShelvesWorks(Edition edition) {

    // Удаляем из работы, полок и удаляем все заметки

    var queryFindEdition = new Query()
        .addCriteria(
            Criteria
                .where("_id")
                .is(edition.getId())
        );

    var queryFindAllNotes = new Query()
        .addCriteria(
            Criteria
                .where("edition")
                .is(edition)
        );

    var queryFindAllShelves = new Query()
        .addCriteria(
            Criteria
                .where(EDITIONS_FIELD_NAME)
                .in(edition)
        );

    var queryFindAllWorks = new Query()
        .addCriteria(
            Criteria
                .where(EDITIONS_FIELD_NAME)
                .in(edition)
        );

    var queryPullFromEditions =
        new Update().pull(EDITIONS_FIELD_NAME, edition);

    // удаление всех заметок
    mongoTemplate.findAllAndRemove(queryFindAllNotes, Note.class);

    // удаление из списка у объекта работы
    mongoTemplate.updateMulti(queryFindAllWorks, queryPullFromEditions, Work.class);

    // удаление из списков полок
    mongoTemplate.updateMulti(queryFindAllShelves, queryPullFromEditions, Shelf.class);

    // удаление издания
    mongoTemplate.findAndRemove(queryFindEdition, Edition.class);

  }
}
