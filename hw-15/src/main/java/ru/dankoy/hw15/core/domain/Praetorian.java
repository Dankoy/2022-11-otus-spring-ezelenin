package ru.dankoy.hw15.core.domain;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

// Stage 6
@ToString
@Getter
@RequiredArgsConstructor
public class Praetorian {

  private static final XenomorphType type = XenomorphType.PRAETORIAN;

  private final String name;

  private final long age;

}
