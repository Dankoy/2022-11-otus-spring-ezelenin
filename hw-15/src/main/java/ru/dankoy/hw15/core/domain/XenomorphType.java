package ru.dankoy.hw15.core.domain;

import java.util.List;
import java.util.Random;

public enum XenomorphType {
  OVOMORPH,
  FACEHUGGER,
  CHESTBURSTER,
  DRONE,
  WARRIOR,
  SENTRY,
  PRAETORIAN,
  QUEEN;

  private static final List<XenomorphType> VALUES = List.of(XenomorphType.SENTRY,
      XenomorphType.WARRIOR);
  private static final int SIZE = VALUES.size();
  private static final Random RANDOM = new Random();

  public static XenomorphType randomOfSentryOrWarrior() {
    return VALUES.get(RANDOM.nextInt(SIZE));
  }

}
