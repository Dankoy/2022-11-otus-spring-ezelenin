package ru.dankoy.hw15.core.gateway;


import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.dankoy.hw15.core.domain.Ovomorph;
import ru.dankoy.hw15.core.domain.Praetorian;

@MessagingGateway
public interface XenoGateway {

  @Gateway(requestChannel = "xenoChannel", replyChannel = "xenoOutputChannel")
  Praetorian process(Ovomorph ovomorph);

}
