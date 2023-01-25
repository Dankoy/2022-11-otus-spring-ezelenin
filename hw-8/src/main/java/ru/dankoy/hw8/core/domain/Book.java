package ru.dankoy.hw8.core.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
public class Book {


  private String id;

  private String name;


  private Set<Author> authors = new HashSet<>();


  private Set<Genre> genres = new HashSet<>();


  @JsonManagedReference // решает проблему с рекурсивной сериализацией
  @Setter
  private Set<Commentary> commentaries = new HashSet<>();
}
