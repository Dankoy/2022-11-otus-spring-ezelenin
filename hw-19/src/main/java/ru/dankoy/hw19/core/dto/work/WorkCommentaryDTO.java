package ru.dankoy.hw19.core.dto.work;


import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.dankoy.hw19.core.domain.Work;

@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WorkCommentaryDTO {

  @NotEmpty
  private String id;

  public static Work fromDTO(WorkCommentaryDTO dto) {
    return new Work(dto.id);
  }

}
