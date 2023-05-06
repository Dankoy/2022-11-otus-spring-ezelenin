package ru.dankoy.hw19.core.domain;


import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Document("editions")
public class Edition {

  @Id
  private String id;

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

  @Field("publisher")
  private Publisher publisher;

  @Field("cover")
  private byte cover;

  @Field("isbn10")
  private String isbn10;

  @Field("isbn13")
  private String isbn13;

  @Field("dt_created")
  private LocalDateTime dateCreated;

  @Field("dt_modified")
  private LocalDateTime dateModified;


}
