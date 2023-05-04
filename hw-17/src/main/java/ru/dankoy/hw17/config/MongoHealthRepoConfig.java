package ru.dankoy.hw17.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = {"ru.dankoy.hw17.core.healthcheck.mongock.repository"})
public class MongoHealthRepoConfig {

}
