package ru.dankoy.hw19.core.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
// фиксит рекурсивный вызов equals и hashcode комментария и книги
@Getter
@Document("commentaries")
public class Commentary extends AuditMetadata {

  @Id
  private String id;

  @Field("text")
  private String text;

  @DBRef(lazy = true)
  @JsonBackReference
  private Work work;

}
