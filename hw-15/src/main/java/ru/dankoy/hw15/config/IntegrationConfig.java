package ru.dankoy.hw15.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import ru.dankoy.hw15.core.service.XenomorphingService;


@Configuration
public class IntegrationConfig {

  @Bean
  public QueueChannel xenoChannel() {
    return MessageChannels.queue(10).get();
  }

  @Bean
  public DirectChannel xenoOutputChannel() {
    return MessageChannels.direct().get();
  }

//  @Bean
//  public PublishSubscribeChannel xenoOutputChannel() {
//    return MessageChannels.publishSubscribe().get();
//  }
//
//  @Bean(name = PollerMetadata.DEFAULT_POLLER)
//  public PollerMetadata poller() {
//    return Pollers.fixedRate(100).maxMessagesPerPoll(2).get();
//  }


  @Bean
  public IntegrationFlow morphingFlow(XenomorphingService xenomorphingService) {
    return IntegrationFlows
        .from(xenoChannel())
        .handle(xenomorphingService, "eggMorphing")
        .handle(xenomorphingService, "facehuggerMorphing")
        .handle(xenomorphingService, "chestbursterMorphing")
        .handle(xenomorphingService, "droneMorphing")
        .handle(xenomorphingService, "warriorMorphing")
        .channel(xenoOutputChannel())
        .get();
  }

}
