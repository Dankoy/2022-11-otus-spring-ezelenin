package ru.dankoy.hw19.core.mongock.changelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.List;
import java.util.Set;
import org.bson.Document;

@ChangeLog(order = "001")
public class InitAuthorizationData {

  @ChangeSet(order = "001", id = "dropDb", author = "dankoy", runAlways = true)
  public void dropDb(MongoDatabase db) {
    db.drop();
  }

  @ChangeSet(order = "002", id = "insertRoles", author = "dankoy")
  public void insertRoles(MongoDatabase db) {

    MongoCollection<Document> roles = db.getCollection("roles");

    var roleAdmin = new Document().append("role", "ROLE_ADMIN");
    var roleReader = new Document().append("role", "ROLE_READER");
    var roleOperator = new Document().append("role", "ROLE_OPERATOR");

    roles.insertMany(List.of(roleAdmin, roleReader, roleOperator));

  }


  @ChangeSet(order = "003", id = "insertUsers", author = "dankoy")
  public void insertUsers(MongoDatabase db) {

    var passwordHash = "$2a$10$TWU4IJ6sZhHeKKNtznMqe.7AqaCRESc68LhExRCs.frwpv.i8uvsW";

    MongoCollection<Document> users = db.getCollection("users");

    var roleAdmin = MongockHelper.getDocumentByName(db, "role", "ROLE_ADMIN", "roles");
    var roleOperator = MongockHelper.getDocumentByName(db, "role", "ROLE_OPERATOR", "roles");
    var roleReader = MongockHelper.getDocumentByName(db, "role", "ROLE_READER", "roles");

    var userAdmin = new Document()
        .append("username", "admin")
        .append("password", passwordHash)
        .append("enabled", true)
        .append("account_non_locked", true)
        .append("account_non_expired", true)
        .append("credentials_non_expired", true)
        .append("roles", Set.of(roleAdmin.get("_id")));

    var userOperator = new Document()
        .append("username", "operator")
        .append("password", passwordHash)
        .append("enabled", true)
        .append("account_non_locked", true)
        .append("account_non_expired", true)
        .append("credentials_non_expired", true)
        .append("roles", Set.of(roleOperator.get("_id")));

    var userReader = new Document()
        .append("username", "reader")
        .append("password", passwordHash)
        .append("enabled", true)
        .append("account_non_locked", true)
        .append("account_non_expired", true)
        .append("credentials_non_expired", true)
        .append("roles", Set.of(roleReader.get("_id")));

    var userTurtle = new Document()
        .append("username", "turtle")
        .append("password", passwordHash)
        .append("enabled", true)
        .append("account_non_locked", true)
        .append("account_non_expired", true)
        .append("credentials_non_expired", true)
        .append("roles", Set.of(roleOperator.get("_id")));

    users.insertMany(List.of(userAdmin, userOperator, userReader, userTurtle));

  }

}
