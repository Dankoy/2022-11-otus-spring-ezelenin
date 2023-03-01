package ru.dankoy.hw11.core.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
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
@Document("authors")
@JsonInclude(Include.NON_EMPTY)
public class Author {

  @Id
  private String id;

  @Field("name")
  private String name;

  public Author(String id) {
    this.id = id;
  }

}
