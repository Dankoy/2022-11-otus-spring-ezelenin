package ru.dankoy.hw19.core.dto.commentary;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentaryModifyDTO {

  private String id;

  private String text;

}
