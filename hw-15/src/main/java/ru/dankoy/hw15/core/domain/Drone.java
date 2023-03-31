package ru.dankoy.hw15.core.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

// Stage 4
@ToString
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Drone {

  private static final XenomorphType type = XenomorphType.DRONE;

  private final String name;

  private final long age;

  @Builder.Default
  private boolean fedEnoughForEvolution = false;

  @Builder.Default
  private XenomorphType predispositionXenoType = XenomorphType.WARRIOR;

}
