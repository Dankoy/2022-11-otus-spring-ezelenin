package ru.dankoy.hw19.core.domain;


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
@EqualsAndHashCode(callSuper = true)
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document("notes")
public class Note extends AuditMetadata {

  @Id
  private String id;


  @DocumentReference(lookup = "{ '_id' : ?#{#target} }")
  @Field("edition")
  private Edition edition;

  @Field("text")
  private String text;


}
