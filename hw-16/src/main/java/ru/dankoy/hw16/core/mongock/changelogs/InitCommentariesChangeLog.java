package ru.dankoy.hw16.core.mongock.changelogs;

import static com.mongodb.client.model.Filters.eq;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.DBRef;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;

@ChangeLog(order = "003")
public class InitCommentariesChangeLog {

  @ChangeSet(order = "001", id = "insertCommentaries", author = "dankoy")
  public void insertCommentaries(MongoDatabase db) {

    MongoCollection<Document> commentaries = db.getCollection("commentaries");

    var book1 = getDocumentByName(db, "book1", "books");
    var book2 = getDocumentByName(db, "book2", "books");

    var com1 = new Document()
        .append("text", "com1")
        .append("book", new DBRef("books", book1.get("_id")));
    var com2 = new Document()
        .append("text", "com2")
        .append("book", new DBRef("books", book1.get("_id")));
    var com3 = new Document()
        .append("text", "com3")
        .append("book", new DBRef("books", book1.get("_id")));
    var com4 = new Document()
        .append("text", "com4")
        .append("book", new DBRef("books", book2.get("_id")));
    var com5 = new Document()
        .append("text", "com5")
        .append("book", new DBRef("books", book2.get("_id")));
    var com6 = new Document()
        .append("text", "com6")
        .append("book", new DBRef("books", book2.get("_id")));

    commentaries.insertMany(List.of(com1, com2, com3, com4, com5, com6));

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
