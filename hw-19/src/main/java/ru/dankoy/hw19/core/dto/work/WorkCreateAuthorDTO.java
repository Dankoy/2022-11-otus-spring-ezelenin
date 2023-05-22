package ru.dankoy.hw19.core.dto.work;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.dankoy.hw19.core.domain.Author;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WorkCreateAuthorDTO {

  private String id;

  public static Author fromDTO(WorkCreateAuthorDTO dto) {

    return new Author(
        dto.getId()
    );

  }

  public static WorkCreateAuthorDTO toDTO(Author author) {

    return new WorkCreateAuthorDTO(author.getId());

  }

}
