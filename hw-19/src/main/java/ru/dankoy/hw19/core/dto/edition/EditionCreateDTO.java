package ru.dankoy.hw19.core.dto.edition;

import java.time.LocalDateTime;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
public class EditionCreateDTO {

  @NotEmpty(message = "work id can't be empty or null")
  @Setter
  private String workId;

  @NotEmpty(message = "name can't be empty or null")
  private String name;

  @NotEmpty(message = "description can't be empty or null")
  private String description;

  @NotNull(message = "published date can't be null")
  private LocalDateTime datePublished;

  @NotEmpty(message = "language can't be empty or null")
  private String language;

  @NotNull(message = "pages can't be empty or null")
  private long pages; // should be wrapper? validation doesn't work for primitives

  @NotNull(message = "publisher can't be null")
  @Valid
  private EditionCreatePublisherDTO publisher;

  private byte cover;

  @NotEmpty(message = "isbn10 can't be empty or null")
  private String isbn10;

  @NotEmpty(message = "isbn13 can't be empty or null")
  private String isbn13;


  public static Edition fromDTO(EditionCreateDTO dto) {
    return new Edition(
        null,
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
