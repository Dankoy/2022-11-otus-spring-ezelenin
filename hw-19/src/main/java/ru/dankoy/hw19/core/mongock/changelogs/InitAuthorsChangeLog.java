package ru.dankoy.hw19.core.mongock.changelogs;


import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.bson.Document;

@ChangeLog(order = "003")
public class InitAuthorsChangeLog {

  @ChangeSet(order = "002", id = "insertAuthors", author = "dankoy")
  public void insertAuthors(MongoDatabase db) {

    var user = MongockHelper.getDocumentByName(db, "username", "turtle", "users");

    MongoCollection<Document> myCollection = db.getCollection("authors");
    List<Document> docs = List.of(
        new Document()
            .append("name", "Graham McNeill")
            .append("birth_date", LocalDate.of(1971, 4, 22))
            .append("death_date", null)
            .append("dt_created", LocalDateTime.now())
            .append("dt_modified", null)
            .append("created_by", user.get("_id"))
            .append("modified_by", null),
        new Document()
            .append("name", "Dan Abnett")
            .append("birth_date", LocalDate.of(1961, 10, 12))
            .append("death_date", null)
            .append("dt_created", LocalDateTime.now())
            .append("dt_modified", null)
            .append("created_by", user.get("_id"))
            .append("modified_by", null)
    );

    myCollection.insertMany(docs);
  }

}
