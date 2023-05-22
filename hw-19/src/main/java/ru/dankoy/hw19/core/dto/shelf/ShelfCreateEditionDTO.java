package ru.dankoy.hw19.core.dto.shelf;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import ru.dankoy.hw19.core.domain.Edition;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
class ShelfCreateEditionDTO {

  @Id
  private String id;

  public static Edition fromDTO(ShelfCreateEditionDTO dto) {
    return new Edition(
        dto.getId(),
        null,
        null,
        null,
        null,
        null,
        0L,
        null,
        (byte) 0x00,
        null,
        null
    );
  }

  public static ShelfCreateEditionDTO toDTO(Edition edition) {
    return ShelfCreateEditionDTO.builder()
        .id(edition.getId())
        .build();
  }

}
