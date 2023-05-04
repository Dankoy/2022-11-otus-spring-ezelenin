package ru.dankoy.hw19.core.mongock.changelogs;

import static com.mongodb.client.model.Filters.eq;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.List;
import java.util.Set;
import org.bson.Document;

@ChangeLog(order = "004")
public class InitAuthorizationData {

  @ChangeSet(order = "001", id = "insertRoles", author = "dankoy")
  public void insertRoles(MongoDatabase db) {

    MongoCollection<Document> roles = db.getCollection("roles");

    var roleAdmin = new Document().append("role", "ROLE_ADMIN");
    var roleReader = new Document().append("role", "ROLE_READER");
    var roleOperator = new Document().append("role", "ROLE_OPERATOR");

    roles.insertMany(List.of(roleAdmin, roleReader, roleOperator));

  }


  @ChangeSet(order = "002", id = "insertUsers", author = "dankoy")
  public void insertUsers(MongoDatabase db) {

    var passwordHash = "$2a$10$TWU4IJ6sZhHeKKNtznMqe.7AqaCRESc68LhExRCs.frwpv.i8uvsW";

    MongoCollection<Document> users = db.getCollection("users");

    var roleAdmin = getDocumentByName(db, "ROLE_ADMIN", "roles");
    var roleOperator = getDocumentByName(db, "ROLE_OPERATOR", "roles");
    var roleReader = getDocumentByName(db, "ROLE_READER", "roles");

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

    users.insertMany(List.of(userAdmin, userOperator, userReader));

  }


  private Document getDocumentByName(MongoDatabase db, String nameValue, String collectionName) {

    MongoCollection<Document> collection = db.getCollection(collectionName);

    return collection.find(eq("role", nameValue))
        .first();

  }

}
