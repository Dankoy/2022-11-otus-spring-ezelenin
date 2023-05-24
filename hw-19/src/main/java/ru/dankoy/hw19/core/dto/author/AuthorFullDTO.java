package ru.dankoy.hw19.core.dto.author;


import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.dankoy.hw19.core.domain.Author;
import ru.dankoy.hw19.core.dto.user.UserMetaDTO;

@ToString
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorFullDTO {

  private String id;

  private String name;

  private LocalDateTime birthDate;

  private LocalDateTime deathDate;

  private LocalDateTime dateCreated;

  private LocalDateTime dateModified;

  private UserMetaDTO createdByUser;

  private UserMetaDTO modifiedByUser;


  public static AuthorFullDTO fromAuthor(Author author) {

    return builder()
        .id(author.getId())
        .name(author.getName())
        .birthDate(author.getBirthDate())
        .deathDate(author.getDeathDate())
        .dateCreated(author.getDateCreated())
        .dateModified(author.getDateModified())
        .createdByUser(UserMetaDTO.toDTO(author.getCreatedByUser()))
        .modifiedByUser(UserMetaDTO.toDTO(author.getModifiedByUser()))
        .build();

  }

  public static Author fromDTO(AuthorFullDTO dto) {

    return new Author(
        dto.id,
        dto.name,
        dto.birthDate,
        dto.deathDate
    );

  }

}
