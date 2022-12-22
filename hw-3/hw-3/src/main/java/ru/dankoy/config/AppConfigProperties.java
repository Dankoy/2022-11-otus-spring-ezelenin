package ru.dankoy.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author turtality
 * <p>
 * Separate configuration just to enable configuration properties {@link AppProperties}
 */
@EnableConfigurationProperties(AppProperties.class)
@Configuration
public class AppConfigProperties {

}
