package ru.dankoy.hw19.core.mongock.changelogs;


import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import org.bson.Document;

@ChangeLog(order = "006")
public class InitShelvesChangeLog {

  @ChangeSet(order = "001", id = "insertShelves", author = "dankoy")
  public void insertPublishers(MongoDatabase db) {

    var user = MongockHelper.getDocumentByName(db, "username", "turtle", "users");

    var edition = MongockHelper.getDocumentByName(db, "name", "Horus Rising", "editions");

    MongoCollection<Document> myCollection = db.getCollection("shelves");
    List<Document> docs = List.of(
        new Document()
            .append("name", "turtle shelf")
            .append("editions", Set.of(edition.get("_id")))
            .append("dt_created", LocalDateTime.now())
            .append("dt_modified", null)
            .append("created_by", user.get("_id"))
            .append("modified_by", null)
    );

    myCollection.insertMany(docs);
  }

}
