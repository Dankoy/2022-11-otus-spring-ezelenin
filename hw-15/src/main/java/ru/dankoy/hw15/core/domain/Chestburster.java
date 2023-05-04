package ru.dankoy.hw15.core.domain;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

//Stage 3
@ToString
@Getter
@RequiredArgsConstructor
public class Chestburster {

  private static final XenomorphType type = XenomorphType.CHESTBURSTER;

  private final String name;

  private final long age;

}
