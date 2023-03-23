package ru.dankoy.hw14.core.domain.mongodb;

import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
@Data
@Document("books")
public class Book {


  @Id
  private String id;


  @Field("name")
  private String name;


  @DocumentReference(lookup = "{ '_id' : ?#{#target} }")
  @Field("authors")
  private Set<Author> authors = new HashSet<>();


  @Field("genres")
  private Set<Genre> genres = new HashSet<>();

}
