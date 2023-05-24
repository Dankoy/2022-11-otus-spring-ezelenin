package ru.dankoy.hw19.core.dto.work;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.dankoy.hw19.core.domain.Edition;

@ToString
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_EMPTY)
public class WorkCreateEditionDTO {

  @NotEmpty
  private String id;

  public static Edition fromDTO(WorkCreateEditionDTO dto) {
    return new Edition(dto.getId());
  }



}
