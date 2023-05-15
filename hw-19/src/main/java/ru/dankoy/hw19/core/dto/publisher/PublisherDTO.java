package ru.dankoy.hw19.core.dto.publisher;


import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.dankoy.hw19.core.domain.Publisher;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PublisherDTO {

  private String id;

  private String name;

  private LocalDateTime dateCreated;

  private LocalDateTime dateModified;

  private String createdByUser;

  private String modifiedByUser;


  public static PublisherDTO toDTO(Publisher publisher) {

    return PublisherDTO.builder()
        .id(publisher.getId())
        .name(publisher.getName())
        .dateCreated(publisher.getDateCreated())
        .dateModified(publisher.getDateModified())
        .createdByUser(publisher.getCreatedByUser())
        .modifiedByUser(publisher.getModifiedByUser())
        .build();

  }

  public static Publisher fromDTO(PublisherDTO dto) {

    return new Publisher(
        dto.getId(),
        dto.getName(),
        dto.getDateCreated(),
        dto.getDateModified(),
        dto.getCreatedByUser(),
        dto.getModifiedByUser()
    );

  }

}
