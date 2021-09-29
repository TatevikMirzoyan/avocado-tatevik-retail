package com.avocado.tatevik.retail.config;

import org.apache.juli.logging.LogFactory;
import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationInitializer;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class MigrationConfig {

    Logger logger = LoggerFactory.getLogger(MigrationConfig.class);

//    @Bean
//    public FlywayMigrationStrategy flywayMigrationStrategy() {
//        return flyway -> {
//            logger.info("Skipping Flyway migration!");
//        };
//    }

//    @Bean(initMethod = "migrate")
//    public Flyway flyway() {
//        Flyway flyway = Flyway.configure().load();
//        flyway.repair();
//        return flyway;
//    }

//    @Bean
//    FlywayMigrationInitializer flywayInitializer(Flyway flyway) {
//        return new FlywayMigrationInitializer(flyway, (f) -> {
//        });
//    }

//    @Bean
//    @DependsOn("entityManagerFactory")
//    FlywayMigrationInitializer delayedFlywayInitializer(Flyway flyway) {
//        return new FlywayMigrationInitializer(flyway);
//    }
}
