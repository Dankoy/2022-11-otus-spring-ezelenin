package ru.dankoy.hw8.core.mongock.changelogs;


import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.List;
import org.bson.Document;

@ChangeLog(order = "002")
public class InitAuthorsChangeLog {

  @ChangeSet(order = "0001", id = "insertAuthors", author = "dankoy")
  public void insertAuthors(MongoDatabase db) {
    MongoCollection<Document> myCollection = db.getCollection("authors");
    List<Document> docs = List.of(
        new Document().append("name", "author1"),
        new Document().append("name", "author2"),
        new Document().append("name", "author3"),
        new Document().append("name", "'author_without_book'")
    );

    myCollection.insertMany(docs);
  }

}
