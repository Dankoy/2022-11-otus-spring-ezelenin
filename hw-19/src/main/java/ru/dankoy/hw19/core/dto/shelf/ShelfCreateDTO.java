package ru.dankoy.hw19.core.dto.shelf;


import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.dankoy.hw19.core.domain.Shelf;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ShelfCreateDTO {


  @NotEmpty
  private String name;

  @Valid
  private Set<ShelfCreateEditionDTO> editions;


  public static Shelf fromDTO(ShelfCreateDTO dto) {

    return new Shelf(
        null,
        dto.name,
        dto.editions.stream().map(ShelfCreateEditionDTO::fromDTO).collect(Collectors.toSet())
    );

  }

}
