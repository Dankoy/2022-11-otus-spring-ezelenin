package ru.dankoy.hw19.core.dto.publisher;


import java.time.LocalDateTime;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.dankoy.hw19.core.domain.Publisher;
import ru.dankoy.hw19.core.dto.user.UserMetaDTO;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PublisherFullDTO {

  private String id;

  private String name;

  private LocalDateTime dateCreated;

  private LocalDateTime dateModified;

  private UserMetaDTO createdByUser;

  private UserMetaDTO modifiedByUser;


  public static PublisherFullDTO toDTO(Publisher publisher) {

    return PublisherFullDTO.builder()
        .id(publisher.getId())
        .name(publisher.getName())
        .dateCreated(publisher.getDateCreated())
        .dateModified(publisher.getDateModified())
        .createdByUser(UserMetaDTO.toDTO(publisher.getCreatedByUser()))
        .modifiedByUser(UserMetaDTO.toDTO(publisher.getModifiedByUser()))
        .build();

  }

  public static Publisher fromDTO(PublisherFullDTO dto) {

    if (Objects.nonNull(dto)) {
      return new Publisher(
          dto.getId(),
          dto.getName()
      );
    } else {
      return new Publisher();
    }

  }

}
