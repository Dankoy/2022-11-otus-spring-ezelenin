package ru.dankoy.hw19.core.dto.note;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.dankoy.hw19.core.domain.Note;

@JsonInclude(Include.NON_EMPTY)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class NoteUpdateDTO {

  private String id;

  private NoteCreateEditionDTO edition;

  private String text;


  public static Note fromDTO(NoteUpdateDTO dto) {

    return new Note(
        dto.getId(),
        NoteCreateEditionDTO.fromDTO(dto.getEdition()),
        dto.getText()
    );

  }

}
