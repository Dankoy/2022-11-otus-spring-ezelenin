package ru.dankoy.hw19.core.dto.edition;


import javax.validation.constraints.NotEmpty;
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

  @NotEmpty(message = "id can't be empty or null")
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
