package ru.dankoy.hw8.core.mongock.changelogs;

import static com.mongodb.client.model.Filters.eq;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;
import ru.dankoy.hw8.core.domain.Commentary;

@ChangeLog(order = "003")
public class InitCommentariesChangeLog {

  @ChangeSet(order = "0001", id = "insertBooks", author = "dankoy")
  public void updateBooksWithCommentaries(MongoDatabase db) {

    MongoCollection<Document> books = db.getCollection("books");

    var book1 = getDocumentByName(db, "book1", "books");
    var book2 = getDocumentByName(db, "book2", "books");

    books.updateOne(book1, book1.append("commentaries",
        List.of(
            new Commentary(null, "com1"),
            new Commentary(null, "com2"),
            new Commentary(null, "com3")
        )));

    books.updateOne(book2, book2.append("commentaries",
        List.of(
            new Commentary(null, "com4"),
            new Commentary(null, "com5"),
            new Commentary(null, "com6")
        )));

  }


  private Document getDocumentByName(MongoDatabase db, String nameValue, String collectionName) {

    MongoCollection<Document> collection = db.getCollection(collectionName);

    Bson projectionFields = Projections.fields(
        Projections.include("name"),
        Projections.excludeId());

    return collection.find(eq("name", nameValue))
        .projection(projectionFields)
        .first();

  }

}
