package ru.dankoy.hw8.core.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Document("books")
public class Book {


  @Id
  private String id;


  @Field("name")
  private String name;


  @DBRef(lazy = true)
  @Field("authors")
  private Set<Author> authors = new HashSet<>();


  @Field("genres")
  private Set<Genre> genres = new HashSet<>();

  @Field("commentaries")
  @DBRef(lazy = true)
  private Set<Commentary> commentaries = new HashSet<>();
}
