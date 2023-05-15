package ru.dankoy.hw19.core.dto.edition;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import ru.dankoy.hw19.core.domain.Edition;
import ru.dankoy.hw19.core.domain.Publisher;
import ru.dankoy.hw19.core.domain.Work;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EditionDTO {

  @Id
  private String id;

  private String workId;

  private String name;

  private String description;

  private LocalDateTime datePublished;

  private String language;

  private long pages; // should be wrapper?

  private Publisher publisher;

  private byte cover;

  private String isbn10;

  private String isbn13;

  private LocalDateTime dateCreated;

  private LocalDateTime dateModified;

  private String createdByUser;

  private String modifiedByUser;

  public static Edition fromDTO(EditionDTO dto) {
    return new Edition(
        dto.getId(),
        new Work(dto.getId()),
        dto.getName(),
        dto.getDescription(),
        dto.getDatePublished(),
        dto.getLanguage(),
        dto.getPages(),
        dto.getPublisher(),
        dto.getCover(),
        dto.getIsbn10(),
        dto.getIsbn13(),
        dto.getDateCreated(),
        dto.getDateModified(),
        dto.getCreatedByUser(),
        dto.getModifiedByUser()
    );
  }

  public static EditionDTO toDTO(Edition edition) {
    return EditionDTO.builder()
        .id(edition.getId())
        .name(edition.getName())
        .description(edition.getDescription())
        .cover(edition.getCover())
        .isbn10(edition.getIsbn10())
        .isbn13(edition.getIsbn13())
        .pages(edition.getPages())
        .language(edition.getLanguage())
        .publisher(edition.getPublisher())
        .datePublished(edition.getDatePublished())
        .createdByUser(edition.getCreatedByUser())
        .modifiedByUser(edition.getModifiedByUser())
        .dateCreated(edition.getDateCreated())
        .dateModified(edition.getDateModified())
        .workId(edition.getWork().getId())
        .build();
  }

}
