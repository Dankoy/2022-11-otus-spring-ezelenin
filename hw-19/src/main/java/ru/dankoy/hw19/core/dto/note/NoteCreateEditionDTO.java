package ru.dankoy.hw19.core.dto.note;

import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.dankoy.hw19.core.domain.Edition;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
class NoteCreateEditionDTO {

  @NotEmpty
  private String id;

  public static Edition fromDTO(NoteCreateEditionDTO dto) {
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

  public static NoteCreateEditionDTO toDTO(Edition edition) {
    return NoteCreateEditionDTO.builder()
        .id(edition.getId())
        .build();
  }

}
