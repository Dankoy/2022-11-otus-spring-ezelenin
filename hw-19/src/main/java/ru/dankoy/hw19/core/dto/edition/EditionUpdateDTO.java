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

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EditionUpdateDTO {

  @Id
  private String id;

  @Setter
  private String workId;

  private String name;

  private String description;

  private LocalDateTime datePublished;

  private String language;

  private long pages; // should be wrapper?

  private EditionCreatePublisherDTO publisher;

  private byte cover;

  private String isbn10;

  private String isbn13;


  public static Edition fromDTO(EditionUpdateDTO dto) {
    return new Edition(
        dto.getId(),
        new Work(dto.getWorkId()),
        dto.getName(),
        dto.getDescription(),
        dto.getDatePublished(),
        dto.getLanguage(),
        dto.getPages(),
        EditionCreatePublisherDTO.fromDTO(dto.getPublisher()),
        dto.getCover(),
        dto.getIsbn10(),
        dto.getIsbn13()
    );
  }

}
