package ru.dankoy.hw11.core.mongock.changelogs;

import static com.mongodb.client.model.Filters.eq;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.DBRef;
import com.mongodb.reactivestreams.client.MongoCollection;
import com.mongodb.reactivestreams.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import com.sun.net.httpserver.Authenticator.Success;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.reactivestreams.Publisher;
import ru.dankoy.hw11.core.mongock.changelogs.SubscriberHelpers.ObservableSubscriber;
import ru.dankoy.hw11.core.mongock.changelogs.SubscriberHelpers.PrintDocumentSubscriber;
import ru.dankoy.hw11.core.repository.book.BookRepository;


@RequiredArgsConstructor
@ChangeLog(order = "003")
public class InitCommentariesChangeLog {

  private final BookRepository bookRepository;

  @ChangeSet(order = "001", id = "insertCommentaries", author = "dankoy")
  public void insertCommentaries(MongoDatabase db) throws Throwable {

    MongoCollection<Document> commentaries = db.getCollection("commentaries");

    var subscriber = new PrintDocumentSubscriber();

    var book1 = getDocumentByName(db, "book1", "books");
    var book2 = getDocumentByName(db, "book2", "books");

    book1.subscribe(subscriber);
    book2.subscribe(subscriber);

    subscriber.await();

    var com1 = new Document()
        .append("text", "com1")
        .append("book", new DBRef("books", book1.get("_id")));
    var com2 = new Document()
        .append("text", "com2")
        .append("book", new DBRef("books", book1.get("_id")));
    var com3 = new Document()
        .append("text", "com3")
        .append("book", new DBRef("books", book1.get("_id")));
    var com4 = new Document()
        .append("text", "com4")
        .append("book", new DBRef("books", book2.get("_id")));
    var com5 = new Document()
        .append("text", "com5")
        .append("book", new DBRef("books", book2.get("_id")));
    var com6 = new Document()
        .append("text", "com6")
        .append("book", new DBRef("books", book2.get("_id")));

    commentaries.insertMany(List.of(com1, com2, com3, com4, com5, com6));

  }


  private Publisher<Document> getDocumentByName(MongoDatabase db, String nameValue, String collectionName) {

    MongoCollection<Document> collection = db.getCollection(collectionName);

    Bson projectionFields = Projections.fields(
        Projections.include("name"));

    return collection.find(eq("name", nameValue))
        .projection(projectionFields)
        .first();

  }

}
