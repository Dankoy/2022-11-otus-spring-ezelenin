package ru.dankoy.hw19.core.dto.note;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.dankoy.hw19.core.domain.Note;
import ru.dankoy.hw19.core.dto.edition.EditionFullDTO;
import ru.dankoy.hw19.core.dto.user.UserMetaDTO;

@JsonInclude(Include.NON_EMPTY)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class NoteFullDTO {

  private String id;

  private EditionFullDTO edition;

  private String text;

  private LocalDateTime dateCreated;

  private LocalDateTime dateModified;

  private UserMetaDTO createdByUser;

  private UserMetaDTO modifiedByUser;


  public static Note fromDTO(NoteFullDTO dto) {

    return new Note(
        dto.id,
        EditionFullDTO.fromDTO(dto.getEdition()),
        dto.getText()
    );

  }

  public static NoteFullDTO toDTO(Note note) {

    return NoteFullDTO.builder()
        .id(note.getId())
        .edition(EditionFullDTO.toDTO(note.getEdition()))
        .text(note.getText())
        .dateCreated(note.getDateCreated())
        .dateModified(note.getDateModified())
        .build();

  }


}
