package ru.dankoy.hw19.core.dto.publisher;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.dankoy.hw19.core.domain.Publisher;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PublisherCreateDTO {

  private String id;

  private String name;

  public static Publisher fromDTO(PublisherCreateDTO dto) {

    return new Publisher(
        null,
        dto.getName()
    );

  }

}
