package ru.dankoy.hw19.core.mongock.changelogs;

import static com.mongodb.client.model.Filters.eq;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public interface MongockHelper {

  static Document getDocumentByName(MongoDatabase db, String field, String nameValue,
      String collectionName) {

    MongoCollection<Document> collection = db.getCollection(collectionName);

    return collection.find(eq(field, nameValue))
        .first();

  }
}
