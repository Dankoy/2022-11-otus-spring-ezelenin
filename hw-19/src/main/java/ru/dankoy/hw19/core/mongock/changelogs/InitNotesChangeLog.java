package ru.dankoy.hw19.core.mongock.changelogs;


import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.time.LocalDateTime;
import java.util.List;
import org.bson.Document;

@ChangeLog(order = "007")
public class InitNotesChangeLog {

  @ChangeSet(order = "001", id = "insertNotes", author = "dankoy")
  public void insertPublishers(MongoDatabase db) {

    var user = MongockHelper.getDocumentByName(db, "username", "turtle", "users");

    var edition = MongockHelper.getDocumentByName(db, "name", "Horus Rising", "editions");

    MongoCollection<Document> myCollection = db.getCollection("notes");
    List<Document> docs = List.of(
        new Document()
            .append("name", "turtle note")
            .append("edition", edition.get("_id"))
            .append("dt_created", LocalDateTime.now())
            .append("dt_modified", null)
            .append("created_by", user.get("_id"))
            .append("modified_by", null)
    );

    myCollection.insertMany(docs);
  }

}
