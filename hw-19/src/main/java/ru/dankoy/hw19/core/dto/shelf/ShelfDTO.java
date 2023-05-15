package ru.dankoy.hw19.core.dto.shelf;


import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.dankoy.hw19.core.domain.Shelf;
import ru.dankoy.hw19.core.dto.edition.EditionDTO;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ShelfDTO {

  private String id;

  private String name;

  private Set<EditionDTO> editions;

  private LocalDateTime dateCreated;

  private LocalDateTime dateModified;


  public static Shelf fromDTO(ShelfDTO dto) {

    return new Shelf(
        dto.id,
        dto.name,
        null,
        dto.editions.stream().map(EditionDTO::fromDTO).collect(Collectors.toSet()),
        null,
        null
    );

  }

  public static ShelfDTO toDTO(Shelf shelf) {

    return ShelfDTO.builder()
        .id(shelf.getId())
        .name(shelf.getName())
        .editions(shelf.getEditions().stream().map(EditionDTO::toDTO).collect(Collectors.toSet()))
        .dateCreated(shelf.getDateCreated())
        .dateModified(shelf.getDateModified())
        .build();

  }


}
