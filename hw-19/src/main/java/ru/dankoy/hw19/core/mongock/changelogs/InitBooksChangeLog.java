package ru.dankoy.hw19.core.mongock.changelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.InsertManyResult;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.bson.BsonObjectId;
import org.bson.BsonValue;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

@ChangeLog(order = "004")
public class InitBooksChangeLog {

  private final Map<String, String> cacheWorks = new HashMap<>();
  private final Map<String, List<ObjectId>> cacheEditions = new HashMap<>();

  @ChangeSet(order = "001", id = "insertBooks", author = "dankoy")
  public void insertWorks(MongoDatabase db) {

    var author1 = MongockHelper.getDocumentByName(db, "name", "Graham McNeill", "authors");
    var author2 = MongockHelper.getDocumentByName(db, "name", "Dan Abnett", "authors");

    MongoCollection<Document> books = db.getCollection("works");

    var doc1 = new Document()
        .append("name", "Horus Rising")
        .append("description",
            "After thousands of years of expansion and conquest, the imperium of man is at its height. His dream for humanity nearly accomplished, the emperor hands over the reins of power to his warmaster, Horus, and heads back to Terra. But is Horus strong enough to control his fellow commanders and continue the emperor's grand design")
        .append("genres", getHorusHeresyGenres())
        .append("authors", List.of(author2.get("_id")))
        .append("editions", new ArrayList<>())
        .append("dt_created", LocalDateTime.now())
        .append("dt_modified", null);

    var doc2 = new Document()
        .append("name", "False Gods")
        .append("description",
            "The human Imperium stands at its height of glory - thousands of worlds have been brought to heel by the conquering armies of mankind. At the peak of his powers, Warmaster Horus wields absolute control - but can even he resist the corrupting whispers of Chaos?")
        .append("genres", getHorusHeresyGenres())
        .append("authors", List.of(author1.get("_id")))
        .append("editions", new ArrayList<>())
        .append("dt_created", LocalDateTime.now())
        .append("dt_modified", null);

    var res1 = books.insertOne(doc1);
    var res2 = books.insertOne(doc2);

    cacheWorks.put("Horus Rising Work", res1.getInsertedId().asObjectId().getValue().toString());
    cacheWorks.put("False Gods Work", res2.getInsertedId().asObjectId().getValue().toString());
  }

  @ChangeSet(order = "002", id = "insertEditions", author = "dankoy")
  public void insertEditions(MongoDatabase db) {

    var work1 = MongockHelper.getDocumentByName(db, "name", "Horus Rising", "works");
    var work2 = MongockHelper.getDocumentByName(db, "name", "False Gods", "works");
    var publisher1 = MongockHelper.getDocumentByName(db, "name", "Black Library", "publishers");

    MongoCollection<Document> editions = db.getCollection("editions");
    List<Document> docs = List.of(
        new Document()
            .append("work", work1.get("_id"))
            .append("name", "Horus Rising")
            .append("description",
                "After thousands of years of expansion and conquest, the imperium of man is at its height. His dream for humanity nearly accomplished, the emperor hands over the reins of power to his warmaster, Horus, and heads back to Terra. But is Horus strong enough to control his fellow commanders and continue the emperor's grand design")
            .append("date_published", LocalDate.of(2006, 4, 25))
            .append("language", "English")
            .append("pages", 416)
            .append("isbn13", "9781844162949")
            .append("isbn10", "184416294X")
            .append("publisher", new Document().append("_id", publisher1.get("_id")))
            .append("dt_created", LocalDateTime.now())
            .append("dt_modified", null)
    );

    List<Document> docs2 = List.of(
        new Document()
            .append("work", work2.get("_id"))
            .append("name", "False Gods")
            .append("description",
                "The human Imperium stands at its height of glory - thousands of worlds have been brought to heel by the conquering armies of mankind. At the peak of his powers, Warmaster Horus wields absolute control - but can even he resist the corrupting whispers of Chaos?")
            .append("date_published", LocalDate.of(2006, 7, 1))
            .append("language", "English")
            .append("pages", 416)
            .append("isbn13", "9781844163700")
            .append("isbn10", "1844163709")
            .append("publisher", new Document().append("_id", publisher1.get("_id")))
            .append("dt_created", LocalDateTime.now())
            .append("dt_modified", null)
    );

    InsertManyResult res1 = editions.insertMany(docs);
    InsertManyResult res2 = editions.insertMany(docs2);

    cacheEditions.put(work1.get("_id").toString(),
        res1.getInsertedIds().values().stream()
            .map(BsonValue::asObjectId)
            .map(BsonObjectId::getValue)
            .collect(Collectors.toList()));

    cacheEditions.put(work2.get("_id").toString(),
        res2.getInsertedIds().values().stream()
            .map(BsonValue::asObjectId)
            .map(BsonObjectId::getValue)
            .collect(Collectors.toList()));

  }

  @ChangeSet(order = "003", id = "updateWorksWitEditions", author = "dankoy")
  public void addEditionToWork(MongoDatabase db) {

    MongoCollection<Document> works = db.getCollection("works");

    var workId1 = cacheWorks.get("Horus Rising Work");
    var workId2 = cacheWorks.get("False Gods Work");

    var workToUpdate1 = new Document()
        .append("_id", new ObjectId(workId1));

    var workToUpdate2 = new Document()
        .append("_id", new ObjectId(workId2));

    var cachedEditionsIds1 = cacheEditions.get(workId1);
    var cachedEditionsIds2 = cacheEditions.get(workId2);

    Bson updates1 = Updates.set("editions", cachedEditionsIds1);
    Bson updates2 = Updates.set("editions", cachedEditionsIds2);

    works.updateOne(workToUpdate1, updates1);
    works.updateOne(workToUpdate2, updates2);

  }

  private List<Document> getHorusHeresyGenres() {
    return List.of(
        new Document().append("name", "Science Fiction"),
        new Document().append("name", "Fantasy"),
        new Document().append("name", "Military Fiction")
    );
  }

}
