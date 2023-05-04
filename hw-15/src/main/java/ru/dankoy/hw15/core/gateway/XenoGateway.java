package ru.dankoy.hw15.core.gateway;


import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.handler.annotation.Header;
import ru.dankoy.hw15.core.domain.Ovomorph;
import ru.dankoy.hw15.core.domain.Praetorian;
import ru.dankoy.hw15.core.domain.Queen;

@MessagingGateway
public interface XenoGateway {

  @Gateway(requestChannel = "stage1Channel", replyChannel = "xenoOutputChannel")
  Praetorian process(Ovomorph ovomorph);


  // Добавляет заголовок при передаче ovomorph, если есть живые существа вокруг, то из яйца успешно
  // вылупляется facehugger и атакует жертву для дальнейшего развития. Если же жертв нет,
  // то такой facehugger погибает
  @Gateway(requestChannel = "stage1Channel", replyChannel = "xenoOutputChannel")
  Queen processWithHeader(@Header("victimsAround") boolean victimsAround, Ovomorph ovomorph);

}
