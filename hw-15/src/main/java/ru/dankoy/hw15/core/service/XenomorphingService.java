package ru.dankoy.hw15.core.service;

import ru.dankoy.hw15.core.domain.Chestburster;
import ru.dankoy.hw15.core.domain.Crusher;
import ru.dankoy.hw15.core.domain.Drone;
import ru.dankoy.hw15.core.domain.Facehugger;
import ru.dankoy.hw15.core.domain.Ovomorph;
import ru.dankoy.hw15.core.domain.Praetorian;
import ru.dankoy.hw15.core.domain.Sentry;
import ru.dankoy.hw15.core.domain.Warrior;

public interface XenomorphingService {


  // from egg to facehugger
  Facehugger eggMorphing(Ovomorph ovomorph);

  Chestburster facehuggerMorphing(Facehugger facehugger);

  Drone chestbursterMorphing(Chestburster chestburster);

  // Drone can morph to other species too.
  Warrior droneToWarriorMorphing(Drone drone);

  Sentry droneToSentryMorphing(Drone drone);

  Praetorian warriorMorphing(Warrior warrior);

  Crusher sentryMorphing(Sentry sentry);

}
