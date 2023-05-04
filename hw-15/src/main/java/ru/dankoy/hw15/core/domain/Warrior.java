package ru.dankoy.hw15.core.domain;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

// Stage 5
@ToString
@Getter
@RequiredArgsConstructor
public class Warrior {

  private static final XenomorphType type = XenomorphType.WARRIOR;

  private final String name;

  private final long age;

}
