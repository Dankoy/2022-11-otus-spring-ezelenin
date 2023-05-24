package ru.dankoy.hw19.core.dto.shelf;


import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.dankoy.hw19.core.domain.Shelf;
import ru.dankoy.hw19.core.dto.user.UserMetaDTO;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ShelfCreatedDTO {

  private String id;

  private String name;

  private Set<ShelfCreateEditionDTO> editions;

  private LocalDateTime dateCreated;

  private LocalDateTime dateModified;

  private UserMetaDTO createdByUser;

  private UserMetaDTO modifiedByUser;


  public static ShelfCreatedDTO toDTO(Shelf shelf) {

    return ShelfCreatedDTO.builder()
        .id(shelf.getId())
        .name(shelf.getName())
        .editions(shelf.getEditions().stream().map(ShelfCreateEditionDTO::toDTO)
            .collect(Collectors.toSet()))
        .dateCreated(shelf.getDateCreated())
        .dateModified(shelf.getDateModified())
        .createdByUser(UserMetaDTO.toDTO(shelf.getCreatedByUser()))
        .modifiedByUser(UserMetaDTO.toDTO(shelf.getModifiedByUser()))
        .build();

  }


}
