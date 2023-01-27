package ru.dankoy.hw8.core.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;


// Вложенный в книги
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
public class Genre {

  @Field("name")
  private String name;

}
