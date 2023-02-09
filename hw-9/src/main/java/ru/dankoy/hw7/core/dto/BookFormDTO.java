package ru.dankoy.hw7.core.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.Set;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonInclude(Include.NON_EMPTY)
public class BookFormDTO {

  private final long id;

  private final String name;

  private final Set<Long> authors;

  private final Set<Long> genres;


}
