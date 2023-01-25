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

@ChangeLog(order = "003")
public class InitBooksChangeLog {

  @ChangeSet(order = "0001", id = "insertBooks", author = "dankoy")
  public void insertBooks(MongoDatabase db) {

    var genre1 = getDocumentByName(db, "genre1", "genres");
    var genre2 = getDocumentByName(db, "genre2", "genres");
    var genre3 = getDocumentByName(db, "genre3", "genres");
    var author1 = getDocumentByName(db, "author1", "authors");
    var author2 = getDocumentByName(db, "author2", "authors");
    var author3 = getDocumentByName(db, "author3", "authors");

    MongoCollection<Document> books = db.getCollection("books");
    List<Document> docs = List.of(
        new Document().append("name", "book1")
            .append("genres", List.of(genre1, genre2))
            .append("authors", List.of(author1, author2)),
        new Document().append("name", "book2")
            .append("genres", List.of(genre2, genre3))
            .append("authors", List.of(author2, author3)),
        new Document().append("name", "book3")
            .append("genres", List.of(genre1, genre3))
            .append("authors", List.of(author1, author3))
    );

    books.insertMany(docs);
  }


  private Document getDocumentByName(MongoDatabase db, String genreName, String collectionName) {

    MongoCollection<Document> genres = db.getCollection(collectionName);

    Bson projectionFields = Projections.fields(
        Projections.include("name"),
        Projections.excludeId());

    return genres.find(eq("name", genreName))
        .projection(projectionFields)
        .first();

  }

}
