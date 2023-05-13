package ru.dankoy.hw19.core.domain;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;


@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Document("works")
public class Work {

  @Id
  private String id;

  @Field("name")
  private String name;

  @Field("description")
  private String description;

  @DocumentReference(lookup = "{ '_id' : ?#{#target} }")
  @Field("authors")
  private Set<Author> authors = new HashSet<>();

  @Field("genres")
  private Set<Genre> genres = new HashSet<>();

  @DocumentReference(collection = "editions", lookup = "{ '_id' : ?#{#target} }")
  @Field("editions")
  private Set<Edition> editions = new HashSet<>();

  @Field("dt_created")
  private LocalDateTime dateCreated;

  @Field("dt_modified")
  private LocalDateTime dateModified;

}
