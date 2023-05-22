package ru.dankoy.hw19.core.dto.edition;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import ru.dankoy.hw19.core.domain.Edition;
import ru.dankoy.hw19.core.domain.Work;
import ru.dankoy.hw19.core.dto.publisher.PublisherFullDTO;
import ru.dankoy.hw19.core.dto.user.UserMetaDTO;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EditionFullDTO {

  @Id
  private String id;

  @Setter
  private String workId;

  private String name;

  private String description;

  private LocalDateTime datePublished;

  private String language;

  private long pages; // should be wrapper?

  private PublisherFullDTO publisher;

  private byte cover;

  private String isbn10;

  private String isbn13;

  private LocalDateTime dateCreated;

  private LocalDateTime dateModified;

  private UserMetaDTO createdByUser;

  private UserMetaDTO modifiedByUser;

  public static Edition fromDTO(EditionFullDTO dto) {
    return new Edition(
        dto.getId(),
        new Work(dto.getWorkId()),
        dto.getName(),
        dto.getDescription(),
        dto.getDatePublished(),
        dto.getLanguage(),
        dto.getPages(),
        PublisherFullDTO.fromDTO(dto.getPublisher()),
        dto.getCover(),
        dto.getIsbn10(),
        dto.getIsbn13()
    );
  }

  public static EditionFullDTO toDTO(Edition edition) {
    return EditionFullDTO.builder()
        .id(edition.getId())
        .name(edition.getName())
        .description(edition.getDescription())
        .cover(edition.getCover())
        .isbn10(edition.getIsbn10())
        .isbn13(edition.getIsbn13())
        .pages(edition.getPages())
        .language(edition.getLanguage())
        .publisher(PublisherFullDTO.toDTO(edition.getPublisher()))
        .datePublished(edition.getDatePublished())
        .createdByUser(UserMetaDTO.toDTO(edition.getCreatedByUser()))
        .modifiedByUser(UserMetaDTO.toDTO(edition.getModifiedByUser()))
        .dateCreated(edition.getDateCreated())
        .dateModified(edition.getDateModified())
        .workId(edition.getWork().getId())
        .build();
  }

}
