package ru.dankoy.hw15.core.domain;


import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@RequiredArgsConstructor
public class Crusher implements Queen {

  private static final XenomorphType type = XenomorphType.QUEEN;

  private final String name;

  private final long age;

}
