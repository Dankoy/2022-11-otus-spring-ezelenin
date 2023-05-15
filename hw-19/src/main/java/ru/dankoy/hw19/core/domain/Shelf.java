package ru.dankoy.hw19.core.domain;


import java.time.LocalDateTime;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Builder
@Document("shelves")
public class Shelf {

  @Id
  private String id;

  @Field("name")
  private String name;

  @Setter
  @DocumentReference(lookup = "{ '_id' : ?#{#target} }")
  @Field("user")
  private User user; // id

  @DocumentReference(lookup = "{ '_id' : ?#{#target} }")
  @Field("editions")
  private Set<Edition> editions;

  @CreatedDate
  @Setter
  @Field("dt_created")
  private LocalDateTime dateCreated;

  @LastModifiedDate
  @Field("dt_modified")
  private LocalDateTime dateModified;


}
