package ru.dankoy.hw19.core.dto.edition;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.dankoy.hw19.core.domain.Publisher;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class EditionCreatePublisherDTO {

  private String id;

  //
  public static EditionCreatePublisherDTO toDTO(Publisher publisher) {

    return EditionCreatePublisherDTO.builder()
        .id(publisher.getId())
        .build();

  }

  public static Publisher fromDTO(EditionCreatePublisherDTO dto) {

    return new Publisher(
        dto.getId(),
        null
    );

  }

}
