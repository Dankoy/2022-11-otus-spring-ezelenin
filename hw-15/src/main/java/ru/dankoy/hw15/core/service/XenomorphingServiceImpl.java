package ru.dankoy.hw15.core.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.dankoy.hw15.core.domain.Chestburster;
import ru.dankoy.hw15.core.domain.Drone;
import ru.dankoy.hw15.core.domain.Facehugger;
import ru.dankoy.hw15.core.domain.Ovomorph;
import ru.dankoy.hw15.core.domain.Praetorian;
import ru.dankoy.hw15.core.domain.Warrior;


@Slf4j
@Service
public class XenomorphingServiceImpl implements XenomorphingService {

  private static final String LOG_MESSAGE = "Morphing from '{}' to '{}";

  @Override
  public Facehugger eggMorphing(Ovomorph ovomorph) {

    var facehugger = new Facehugger(ovomorph.getName(), ovomorph.getAge() + 1);

    log.info(LOG_MESSAGE, ovomorph, facehugger);

    return facehugger;
  }

  @Override
  public Chestburster facehuggerMorphing(Facehugger facehugger) {

    var chestburster =  new Chestburster(facehugger.getName(), facehugger.getAge() + 2);

    log.info(LOG_MESSAGE, facehugger, chestburster);

    return chestburster;
  }

  @Override
  public Drone chestbursterMorphing(Chestburster chestburster) {
    var drone =  new Drone(chestburster.getName(), chestburster.getAge() + 1);

    log.info(LOG_MESSAGE, chestburster, drone);

    return drone;

  }

  @Override
  public Warrior droneMorphing(Drone drone) {

    var warrior =  new Warrior(drone.getName(), drone.getAge() + 1);

    log.info(LOG_MESSAGE, drone, warrior);

    return warrior;
  }

  @Override
  public Praetorian warriorMorphing(Warrior warrior) {

    var praetorian =  new Praetorian(warrior.getName(), warrior.getAge() + 1);

    log.info(LOG_MESSAGE, warrior, praetorian);

    return praetorian;

  }
}
