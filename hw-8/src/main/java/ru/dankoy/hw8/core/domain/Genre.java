package ru.dankoy.hw8.core.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
public class Genre {

  @Id
  private String id;

  @Field("name")
  private String name;

  public Genre(String id) {
    this.id = id;
  }

}
