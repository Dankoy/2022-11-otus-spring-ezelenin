package ru.dankoy.hw15.core.domain;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

// Stage 4
@ToString
@Getter
@RequiredArgsConstructor
public class Drone {

  private static final XenomorphType type = XenomorphType.DRONE;

  private final String name;

  private final long age;

}
