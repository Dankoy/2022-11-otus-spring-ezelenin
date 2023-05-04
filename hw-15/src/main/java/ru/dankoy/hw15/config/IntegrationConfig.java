package ru.dankoy.hw15.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.router.HeaderValueRouter;
import org.springframework.integration.scheduling.PollerMetadata;
import ru.dankoy.hw15.core.domain.Drone;
import ru.dankoy.hw15.core.domain.XenomorphType;
import ru.dankoy.hw15.core.selector.OvomorphSelector;
import ru.dankoy.hw15.core.service.XenomorphingService;


@Configuration
public class IntegrationConfig {

  // начальный поток в который поступают ovomorphs
  @Bean
  public QueueChannel stage1Channel() {
    return MessageChannels.queue(10).get();
  }


  // Поток в который поступают дроны с заголовком предрасположенности эволюции в warrior
  @Bean
  public QueueChannel stage6WarriorChannel() {
    return MessageChannels.queue(10).get();
  }


  // Поток в который поступают дроны с заголовком предрасположенности эволюции в sentry
  @Bean
  public QueueChannel stage6SentryChannel() {
    return MessageChannels.queue(10).get();
  }


  // общий выходной поток, в который поступают финальные стадии эволюции ксеноморфа
  @Bean
  public PublishSubscribeChannel xenoOutputChannel() {
    return MessageChannels.publishSubscribe().get();
  }

  @Bean(name = PollerMetadata.DEFAULT_POLLER)
  public PollerMetadata poller() {
    return Pollers.fixedRate(100).maxMessagesPerPoll(2).get();
  }

  @Bean
  public HeaderValueRouter droneRouter() {
    HeaderValueRouter router = new HeaderValueRouter("predisposition");
    router.setChannelMapping("WARRIOR", "stage6WarriorChannel");
    router.setChannelMapping("SENTRY", "stage6SentryChannel");
    return router;
  }


  @Bean
  public IntegrationFlow morphingFlow(XenomorphingService xenomorphingService,
      OvomorphSelector ovomorphSelector) {
    return IntegrationFlows
        .from(stage1Channel())
        .handle(xenomorphingService, "eggMorphing")
        // фильтр на заголовок, есть ли жертвы поблизости
        .filter(ovomorphSelector)
        // facehugger морфится в chestburster без дополнительных условий
        .handle(xenomorphingService, "facehuggerMorphing")
        // chestburster морфится в drone без дополнительных условий
        .handle(xenomorphingService, "chestbursterMorphing")
        // допустим, что жертв достаточно, поэтому меняем у объекта дрона готовность к трансформации.
        // По-хорошему нужен отдельный сервис, который бы проверял внешние условия, что жертв
        // достаточно, в зависимости от, например, отдельного объекта, содержащего количество жертв
        // с разными параметрами, которые, в свою очередь влияли на шкалу насыщенности дрона для эволюции
        .<Drone, Drone>transform(drone -> Drone.builder()
            .age(drone.getAge())
            .name(drone.getName())
            .fedEnoughForEvolution(true)
            // добавляет еще и предрасположенность к типу эволюции
            .predispositionXenoType(XenomorphType.randomOfSentryOrWarrior())
            .build())
        // Добавить заголовок в зависимости от предрасположенности
        .enrichHeaders(h -> h.headerExpression(
            "predisposition",
            "#root.payload.predispositionXenoType.name()")
        )
        // В зависимости от заголовка сообщения, происходит роутинг в один из двух каналов по
        // эволюции дрона в преторианца или дробителя
        .route(droneRouter())
        .get();
  }


  // Флоу, описывающий трансформацию дрона в praetorian
  @Bean
  public IntegrationFlow morphingDroneToWarriorFlow(XenomorphingService xenomorphingService) {
    return IntegrationFlows
        .from(stage6WarriorChannel())
        .handle(xenomorphingService, "droneToWarriorMorphing")
        .handle(xenomorphingService, "warriorMorphing")
        .channel(xenoOutputChannel())
        .get();
  }


  // Флоу, описывающий трансформацию дрона в crusher
  @Bean
  public IntegrationFlow morphingDroneToSentryFlow(XenomorphingService xenomorphingService) {
    return IntegrationFlows
        .from(stage6SentryChannel())
        .handle(xenomorphingService, "droneToSentryMorphing")
        .handle(xenomorphingService, "sentryMorphing")
        .channel(xenoOutputChannel())
        .get();
  }

}
