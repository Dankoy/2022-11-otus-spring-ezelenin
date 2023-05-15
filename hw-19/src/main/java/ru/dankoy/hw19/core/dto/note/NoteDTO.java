package ru.dankoy.hw19.core.dto.note;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.dankoy.hw19.core.domain.Note;
import ru.dankoy.hw19.core.dto.edition.EditionDTO;

@JsonInclude(Include.NON_EMPTY)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class NoteDTO {

  private String id;

  private EditionDTO edition;

  private String text;

  private LocalDateTime dateCreated;

  private LocalDateTime dateModified;


  public static Note fromDTO(NoteDTO dto) {

    return new Note(
        dto.id,
        null,
        EditionDTO.fromDTO(dto.getEdition()),
        dto.getText(),
        null,
        null
    );

  }

  public static NoteDTO toDTO(Note note) {

    return NoteDTO.builder()
        .id(note.getId())
        .edition(EditionDTO.toDTO(note.getEdition()))
        .text(note.getText())
        .dateCreated(note.getDateCreated())
        .dateModified(note.getDateModified())
        .build();

  }


}
