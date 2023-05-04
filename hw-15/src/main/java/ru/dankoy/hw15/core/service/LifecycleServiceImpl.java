package ru.dankoy.hw15.core.service;


import java.util.Random;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.dankoy.hw15.core.domain.Ovomorph;
import ru.dankoy.hw15.core.domain.XenomorphType;
import ru.dankoy.hw15.core.gateway.XenoGateway;

@Service
@Slf4j
@RequiredArgsConstructor
public class LifecycleServiceImpl implements LifecycleService {

  private static final Random random = new Random();

  private final XenoGateway xenoGateway;

  @Override
  public void runXenoProcess() {

    for (int i = 0; i < 5; i++) {

      var ovomorph = new Ovomorph(XenomorphType.OVOMORPH.name() + i, i);
      log.info("Egg planted - {}", ovomorph);

      var victimsAround = getBooleanVictimsAround();
//      var victimsAround = true;

      var queen = xenoGateway.processWithHeader(victimsAround, ovomorph);

      log.info("Successfully morphed - {}", queen);

    }

  }

  private boolean getBooleanVictimsAround() {
    return random.nextBoolean();
  }


}
