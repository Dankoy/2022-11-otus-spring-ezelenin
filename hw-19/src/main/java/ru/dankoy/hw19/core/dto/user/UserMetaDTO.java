package ru.dankoy.hw19.core.dto.user;


import java.util.HashSet;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.dankoy.hw19.core.domain.User;

@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserMetaDTO {

  private String id;
  private String username;

  public static User fromDTO(UserMetaDTO dto) {
    return new User(
        dto.getId(),
        dto.username,
        null,
        true,
        true,
        true,
        true,
        new HashSet<>()
    );
  }

  public static UserMetaDTO toDTO(User user) {

    if (Objects.nonNull(user)) {
      return new UserMetaDTO(
          user.getId(),
          user.getUsername()
      );
    } else {
      return new UserMetaDTO();
    }

  }

}
