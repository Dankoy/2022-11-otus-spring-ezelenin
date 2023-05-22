package ru.dankoy.hw19.core.dto.author;


import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.dankoy.hw19.core.domain.Author;

@ToString
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorUpdateDTO {

  private String id;

  private String name;

  private LocalDateTime birthDate;

  private LocalDateTime deathDate;

  public static Author fromDTO(AuthorUpdateDTO dto) {

    return new Author(
        dto.getId(),
        dto.name,
        dto.birthDate,
        dto.deathDate
    );

  }

}
