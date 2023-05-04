package ru.dankoy.hw19.core.domain;


import java.io.Serial;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("roles")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserRole implements Serializable {

  @Serial
  private static final long serialVersionUID = 1905122041950251207L;

  @Id
  @Field(name = "id")
  private String id;

  @Field(name = "role")
  private String role;

}
