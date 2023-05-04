package ru.dankoy.hw15.core.domain;

//Stage 2

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@RequiredArgsConstructor
public class Facehugger {

  private static final XenomorphType type = XenomorphType.FACEHUGGER;

  private final String name;

  private final long age;

}
