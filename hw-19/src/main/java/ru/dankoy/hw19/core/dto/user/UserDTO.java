package ru.dankoy.hw19.core.dto.user;


import java.util.HashSet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.dankoy.hw19.core.domain.User;

@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

  private String id;

  public static User fromDTO(UserDTO dto) {
    return new User(
        dto.id,
        null,
        null,
        false,
        false,
        false,
        false,
        new HashSet<>()
    );
  }

}
