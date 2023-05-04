package ru.dankoy.hw15.core.domain;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;


//Stage 1 - Egg
@ToString
@Getter
@RequiredArgsConstructor
public class Ovomorph {

  private static final XenomorphType type = XenomorphType.OVOMORPH;

  private final String name;

  private final long age;

}
