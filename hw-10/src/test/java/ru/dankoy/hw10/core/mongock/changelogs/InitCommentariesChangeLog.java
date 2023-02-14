package ru.dankoy.hw10.core.mongock.changelogs;

import static com.mongodb.client.model.Filters.eq;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.DBRef;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Updates;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;

@ChangeLog(order = "003")
public class InitCommentariesChangeLog {

  @ChangeSet(order = "001", id = "insertCommentaries", author = "dankoy")
  public void insertCommentaries(MongoDatabase db) {

    MongoCollection<Document> commentaries = db.getCollection("commentaries");
    MongoCollection<Document> books = db.getCollection("commentaries");

    var book1 = getDocumentByName(db, "book1", "books");

    var com1 = new Document()
        .append("text", "com1")
        .append("book", new DBRef("books", book1.get("_id")));
    var com2 = new Document()
        .append("text", "com2")
        .append("book", new DBRef("books", book1.get("_id")));
    var com3 = new Document()
        .append("text", "com3")
        .append("book", new DBRef("books", book1.get("_id")));

    commentaries.insertMany(List.of(com1, com2, com3));

    books.updateOne(
        book1, Updates.set("commentaries", List.of(com1, com2, com3))
    );

  }


  @ChangeSet(order = "002", id = "insertCommentariesToBooks", author = "dankoy")
  public void insertCommentariesToBooks(MongoDatabase db) {

    MongoCollection<Document> commentaries = db.getCollection("commentaries");
    MongoCollection<Document> books = db.getCollection("books");

    var book1 = getDocumentByName(db, "book1", "books");
    var com1 = commentaries.find(eq("text", "com1")).first();
    var com2 = commentaries.find(eq("text", "com2")).first();
    var com3 = commentaries.find(eq("text", "com3")).first();

    books.findOneAndUpdate(
        book1, Updates.set("commentaries", List.of(
            new DBRef("commentaries", com1.get("_id")),
            new DBRef("commentaries", com2.get("_id")),
            new DBRef("commentaries", com3.get("_id"))
        ))
    );

  }


  private Document getDocumentByName(MongoDatabase db, String nameValue, String collectionName) {

    MongoCollection<Document> collection = db.getCollection(collectionName);

    Bson projectionFields = Projections.fields(
        Projections.include("name"));

    return collection.find(eq("name", nameValue))
        .projection(projectionFields)
        .first();

  }

}
