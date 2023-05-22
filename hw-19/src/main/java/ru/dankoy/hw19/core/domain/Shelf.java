package ru.dankoy.hw19.core.domain;


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
@EqualsAndHashCode(callSuper = true)
@Getter
@Document("shelves")
public class Shelf extends AuditMetadata {

  @Id
  private String id;

  @Field("name")
  private String name;

  @DocumentReference(lookup = "{ '_id' : ?#{#target} }")
  @Field("editions")
  private Set<Edition> editions;


}
