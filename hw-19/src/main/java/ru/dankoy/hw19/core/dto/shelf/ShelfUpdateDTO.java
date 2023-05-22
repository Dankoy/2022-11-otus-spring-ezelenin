package ru.dankoy.hw19.core.dto.shelf;


import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.dankoy.hw19.core.domain.Shelf;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ShelfUpdateDTO {

  private String id;

  private String name;

  private Set<ShelfCreateEditionDTO> editions;


  public static Shelf fromDTO(ShelfUpdateDTO dto) {

    return new Shelf(
        dto.getId(),
        dto.name,
        dto.editions.stream().map(ShelfCreateEditionDTO::fromDTO).collect(Collectors.toSet())
    );

  }

}
