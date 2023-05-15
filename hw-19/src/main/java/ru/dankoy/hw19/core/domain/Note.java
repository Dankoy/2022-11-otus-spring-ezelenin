package ru.dankoy.hw19.core.domain;


import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;


@ToString
@EqualsAndHashCode
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document("notes")
public class Note {

  @Id
  private String id;

  @Setter
  @DocumentReference(lookup = "{ '_id' : ?#{#target} }")
  @Field("user")
  private User user;

  @DocumentReference(lookup = "{ '_id' : ?#{#target} }")
  @Field("edition")
  private Edition edition;

  @Field("text")
  private String text;

  @Setter
  @Field("dt_created")
  @CreatedDate
  private LocalDateTime dateCreated;

  @Setter
  @Field("dt_modified")
  @LastModifiedDate
  private LocalDateTime dateModified;

}
