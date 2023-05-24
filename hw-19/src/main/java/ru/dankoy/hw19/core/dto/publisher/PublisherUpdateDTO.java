package ru.dankoy.hw19.core.dto.publisher;


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
public class PublisherUpdateDTO {

  @NotEmpty
  private String id;

  @NotEmpty
  private String name;


  public static Publisher fromDTO(PublisherUpdateDTO dto) {

    return new Publisher(
        dto.getId(),
        dto.getName()
    );

  }

}
