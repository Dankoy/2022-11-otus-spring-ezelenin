package ru.dankoy.hw19.core.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
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
@EqualsAndHashCode(callSuper = true)
@Getter
@Document("authors")
@JsonInclude(Include.NON_EMPTY)
public class Author extends AuditMetadata {

  @Id
  private String id;

  @Field("name")
  private String name;

  @Field("birth_date")
  private LocalDateTime birthDate;

  @Field("death_date")
  private LocalDateTime deathDate;

  public Author(String id) {
    this.id = id;
  }

}
