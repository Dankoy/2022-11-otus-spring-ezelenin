package ru.dankoy.hw15.core.domain;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Sentry {

  private static final XenomorphType type = XenomorphType.SENTRY;

  private final String name;

  private final long age;

}
