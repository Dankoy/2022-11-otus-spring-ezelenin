package ru.dankoy.hw19.core.dto.author;


import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
public class AuthorCreateDTO {

  @NotEmpty(message = "name can't be empty or null")
  private String name;

  @NotNull(message = "birth date can't be null")
  private LocalDateTime birthDate;

  private LocalDateTime deathDate;


  public static AuthorCreateDTO fromAuthor(Author author) {

    return builder()
        .name(author.getName())
        .birthDate(author.getBirthDate())
        .deathDate(author.getDeathDate())
        .build();

  }

  public static Author fromDTO(AuthorCreateDTO dto) {

    return new Author(
        null,
        dto.name,
        dto.birthDate,
        dto.deathDate
    );

  }

}
