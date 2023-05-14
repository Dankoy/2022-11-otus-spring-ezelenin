package ru.dankoy.hw19.core.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;


//@SuperBuilder
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

  @JsonManagedReference
  @DocumentReference(collection = "editions", lookup = "{ '_id' : ?#{#target} }")
  @Field("editions")
  private Set<Edition> editions = new HashSet<>();

  @Setter
  @Field(value = "dt_created")
  @CreatedDate
  private LocalDateTime dateCreated;

  @Field("dt_modified")
  @LastModifiedDate
  private LocalDateTime dateModified;

  @Setter
  @Field(value = "created_by")
  @CreatedBy
  private String createdByUser;

  @Field("modified_by")
  @LastModifiedBy
  private String modifiedByUser;

  public Work(String id) {
    this.id = id;
  }

}
