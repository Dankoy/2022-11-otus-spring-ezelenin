package ru.dankoy.hw19.core.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import ru.dankoy.hw19.core.domain.Publisher;

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

}
