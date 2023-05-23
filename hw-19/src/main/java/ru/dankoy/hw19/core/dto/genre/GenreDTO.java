package ru.dankoy.hw19.core.dto.genre;


import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.dankoy.hw19.core.domain.Genre;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GenreDTO {

  @NotEmpty
  private String name;


  public static Genre fromDTO(GenreDTO dto) {
    return new Genre(dto.getName());
  }

}
