package ru.dankoy.hw19.core.mongock.changelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.DBRef;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.List;
import org.bson.Document;

@ChangeLog(order = "005")
public class InitCommentariesChangeLog {

  @ChangeSet(order = "001", id = "insertCommentaries", author = "dankoy")
  public void insertCommentaries(MongoDatabase db) {

    MongoCollection<Document> commentaries = db.getCollection("commentaries");

    var book1 = MongockHelper.getDocumentByName(db, "name", "Horus Rising", "works");
    var user = MongockHelper.getDocumentByName(db, "username", "turtle", "users");

    var com1 = new Document()
        .append("text", "com1")
        .append("work", new DBRef("works", book1.get("_id")))
        .append("user", user.get("_id"));
    var com2 = new Document()
        .append("text", "com2")
        .append("work", new DBRef("works", book1.get("_id")))
        .append("user", user.get("_id"));
    var com3 = new Document()
        .append("text", "com3")
        .append("work", new DBRef("works", book1.get("_id")))
        .append("user", user.get("_id"));

    commentaries.insertMany(List.of(com1, com2, com3));

  }

}
