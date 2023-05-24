package ru.dankoy.hw19.core.dto.shelf;


import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.dankoy.hw19.core.domain.Shelf;
import ru.dankoy.hw19.core.dto.edition.EditionFullDTO;
import ru.dankoy.hw19.core.dto.user.UserMetaDTO;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ShelfFullDTO {

  private String id;

  private String name;

  private Set<EditionFullDTO> editions;

  private LocalDateTime dateCreated;

  private LocalDateTime dateModified;

  private UserMetaDTO createdByUser;

  private UserMetaDTO modifiedByUser;


  public static Shelf fromDTO(ShelfFullDTO dto) {

    return new Shelf(
        dto.id,
        dto.name,
        dto.editions.stream().map(EditionFullDTO::fromDTO).collect(Collectors.toSet())
    );

  }

  public static ShelfFullDTO toDTO(Shelf shelf) {

    return ShelfFullDTO.builder()
        .id(shelf.getId())
        .name(shelf.getName())
        .editions(shelf.getEditions().stream().map(EditionFullDTO::toDTO).collect(Collectors.toSet()))
        .dateCreated(shelf.getDateCreated())
        .dateModified(shelf.getDateModified())
        .createdByUser(UserMetaDTO.toDTO(shelf.getCreatedByUser()))
        .modifiedByUser(UserMetaDTO.toDTO(shelf.getModifiedByUser()))
        .build();

  }


}
