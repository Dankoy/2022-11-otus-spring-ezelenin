package ru.dankoy.hw8.core.mongock.changelogs;


import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.List;
import org.bson.Document;

@ChangeLog(order = "001")
public class InitGenresChangeLog {

  @ChangeSet(order = "0001", id = "dropDb", author = "dankoy", runAlways = true)
  public void dropDb(MongoDatabase db) {
    db.drop();
  }


  @ChangeSet(order = "0002", id = "insertGenres", author = "dankoy")
  public void insertGenres(MongoDatabase db) {
    MongoCollection<Document> myCollection = db.getCollection("genres");
    List<Document> docs = List.of(
        new Document().append("name", "genre1"),
        new Document().append("name", "genre2"),
        new Document().append("name", "genre3"),
        new Document().append("name", "'genre_without_book'")
    );

    myCollection.insertMany(docs);
  }

}
