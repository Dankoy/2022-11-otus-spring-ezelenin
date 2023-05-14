package ru.dankoy.hw19.core.dto.author;


import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.dankoy.hw19.core.domain.Author;

@ToString
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDTO {

  private String id;

  private String name;

  private LocalDateTime birthDate;

  private LocalDateTime deathDate;

  private LocalDateTime dateCreated;

  private LocalDateTime dateModified;

  private String createdByUser;

  private String modifiedByUser;


  public static AuthorDTO fromAuthor(Author author) {

    return builder()
        .id(author.getId())
        .name(author.getName())
        .birthDate(author.getBirthDate())
        .deathDate(author.getDeathDate())
        .dateCreated(author.getDateCreated())
        .dateModified(author.getDateModified())
        .createdByUser(author.getCreatedByUser())
        .modifiedByUser(author.getModifiedByUser())
        .build();

  }

  public static Author fromDTO(AuthorDTO dto) {

    return new Author(
        dto.id,
        dto.name,
        dto.birthDate,
        dto.deathDate,
        dto.dateCreated,
        dto.dateModified,
        dto.createdByUser,
        dto.modifiedByUser
    );

  }

}
