package ru.dankoy.hw19.core.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

@ToString(exclude = "work")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"work"}, callSuper = true)
@Getter
@Document("editions")
public class Edition extends AuditMetadata {

  @Id
  private String id;

  @JsonBackReference
  @DocumentReference(collection = "works", lookup = "{ '_id' : ?#{#target} }")
  @Field("work")
  private Work work;

  @Field("name")
  private String name;

  @Field("description")
  private String description;

  @Field("date_published")
  private LocalDateTime datePublished;

  @Field("language")
  private String language;

  @Field("pages")
  private long pages; // should be wrapper?

  @DocumentReference(collection = "publishers", lookup = "{ '_id' : ?#{#target} }")
  @Field("publisher")
  private Publisher publisher;

  @Field("cover")
  private byte cover;

  @Field("isbn10")
  private String isbn10;

  @Field("isbn13")
  private String isbn13;

  public Edition(String id) {
    this.id = id;
  }


}
