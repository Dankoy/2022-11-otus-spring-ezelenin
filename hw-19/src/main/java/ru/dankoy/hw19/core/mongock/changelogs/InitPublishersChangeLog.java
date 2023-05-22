package ru.dankoy.hw19.core.mongock.changelogs;


import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.time.LocalDateTime;
import java.util.List;
import org.bson.Document;

@ChangeLog(order = "002")
public class InitPublishersChangeLog {

  @ChangeSet(order = "001", id = "insertPublishers", author = "dankoy")
  public void insertPublishers(MongoDatabase db) {

    var user = MongockHelper.getDocumentByName(db, "username", "turtle", "users");

    MongoCollection<Document> myCollection = db.getCollection("publishers");
    List<Document> docs = List.of(
        new Document()
            .append("name", "Black Library")
            .append("dt_created", LocalDateTime.now())
            .append("dt_created", LocalDateTime.now())
            .append("dt_modified", null)
            .append("created_by", user.get("_id"))
            .append("modified_by", null)
    );

    myCollection.insertMany(docs);
  }

}
