package ru.dankoy.hw16.core.mongock.changelogs;

import static com.mongodb.client.model.Filters.eq;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import java.util.HashSet;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;

@ChangeLog(order = "002")
public class InitBooksChangeLog {

  @ChangeSet(order = "001", id = "insertBooks", author = "dankoy")
  public void insertBooks(MongoDatabase db) {

    var author1 = getDocumentByName(db, "author1", "authors");
    var author2 = getDocumentByName(db, "author2", "authors");

    MongoCollection<Document> books = db.getCollection("books");
    List<Document> docs = List.of(
        new Document().append("name", "book1")
            .append("genres", List.of(
                new Document().append("name", "genre1"),
                new Document().append("name", "genre2")
            ))
            .append("authors", List.of(author1.get("_id"), author2.get("_id")))
            .append("commentaries", new HashSet<>())
    );

    books.insertMany(docs);
  }


  private Document getDocumentByName(MongoDatabase db, String genreName, String collectionName) {

    MongoCollection<Document> genres = db.getCollection(collectionName);

    Bson projectionFields = Projections.fields(
        Projections.include("name"));

    return genres.find(eq("name", genreName))
        .projection(projectionFields)
        .first();

  }

}
