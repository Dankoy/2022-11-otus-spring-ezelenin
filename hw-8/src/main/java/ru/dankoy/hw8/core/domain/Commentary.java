package ru.dankoy.hw8.core.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "book")
// фиксит рекурсивный вызов equals и hashcode комментария и книги
@Getter
@Document("commentary")
public class Commentary {

  @Id
  private String id;

  @Field("text")
  private String text;

  @DBRef
  @JsonBackReference // решает проблему с рекурсивной сериализацией
  private Book book;

}
