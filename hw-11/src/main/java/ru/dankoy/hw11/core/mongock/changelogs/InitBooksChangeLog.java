package ru.dankoy.hw11.core.mongock.changelogs;

import static com.mongodb.client.model.Filters.eq;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.model.Projections;
import com.mongodb.reactivestreams.client.MongoCollection;
import com.mongodb.reactivestreams.client.MongoDatabase;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import ru.dankoy.hw11.core.domain.Author;
import ru.dankoy.hw11.core.mongock.changelogs.SubscriberHelpers.ObservableSubscriber;
import ru.dankoy.hw11.core.repository.author.AuthorRepository;

@RequiredArgsConstructor
@ChangeLog(order = "002")
public class InitBooksChangeLog {

  private static final Logger logger = LoggerFactory.getLogger(InitBooksChangeLog.class);
  private final AuthorRepository authorRepository;

  @ChangeSet(order = "001", id = "insertBooks", author = "dankoy")
  public void insertBooks(MongoDatabase db) {

    Flux<Author> authors = authorRepository.findAllByNames(
        List.of("author1", "author2", "author3"));

    Map<String, String> m = authors.collect(Collectors.toMap(Author::getName, Author::getId))
        .block();

    MongoCollection<Document> books = db.getCollection("books");
    List<Document> docs = List.of(
        new Document().append("name", "book1")
            .append("genres", List.of(
                new Document().append("name", "genre1"),
                new Document().append("name", "genre2")
            ))
            .append("authors", List.of(m.get("author1"), m.get("author2"))),
        new Document().append("name", "book2")
            .append("genres", List.of(
                new Document().append("name", "genre2"),
                new Document().append("name", "genre3")
            ))
            .append("authors", List.of(m.get("author2"), m.get("author3"))),
        new Document().append("name", "book3")
            .append("genres", List.of(
                new Document().append("name", "genre1"),
                new Document().append("name", "genre3")
            ))
            .append("authors", List.of(m.get("author1"), m.get("author3")))
    );

    books.insertMany(docs)
        .subscribe(new ObservableSubscriber<>());
  }


  private Publisher<Document> getDocumentByName(MongoDatabase db, String genreName,
      String collectionName) {

    MongoCollection<Document> genres = db.getCollection(collectionName);

    Bson projectionFields = Projections.fields(
        Projections.include("name"));

    return genres.find(eq("name", genreName))
        .projection(projectionFields)
        .first();

  }

}
