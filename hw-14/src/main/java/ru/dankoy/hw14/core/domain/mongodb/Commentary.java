package ru.dankoy.hw14.core.domain.mongodb;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Data
@Document("commentaries")
public class Commentary {

  @Id
  private String id;

  @Field("text")
  private String text;

  @DBRef(lazy = true)
  @JsonBackReference
  private Book book;

}
