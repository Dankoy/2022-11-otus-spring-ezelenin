package ru.dankoy.hw19.core.dto.note;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.dankoy.hw19.core.domain.Note;
import ru.dankoy.hw19.core.dto.user.UserMetaDTO;

@JsonInclude(Include.NON_EMPTY)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class NoteCreatedDTO {

  private String id;

  private NoteCreateEditionDTO edition;

  private String text;

  private LocalDateTime dateCreated;

  private LocalDateTime dateModified;

  private UserMetaDTO createdByUser;

  private UserMetaDTO modifiedByUser;


  public static Note fromDTO(NoteCreatedDTO dto) {

    return new Note(
        null,
        NoteCreateEditionDTO.fromDTO(dto.getEdition()),
        dto.getText()
    );

  }

  public static NoteCreatedDTO toDTO(Note note) {

    return NoteCreatedDTO.builder()
        .id(note.getId())
        .edition(NoteCreateEditionDTO.toDTO(note.getEdition()))
        .text(note.getText())
        .dateCreated(note.getDateCreated())
        .dateModified(note.getDateModified())
        .createdByUser(UserMetaDTO.toDTO(note.getCreatedByUser()))
        .modifiedByUser(UserMetaDTO.toDTO(note.getModifiedByUser()))
        .build();

  }


}
