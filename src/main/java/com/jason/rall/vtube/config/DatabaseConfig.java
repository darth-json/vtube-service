package com.jason.rall.vtube.config;

import com.github.mongobee.Mongobee;
import com.mongodb.MongoClient;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 *
 * @author jrall
 */
@Configuration
@EnableMongoRepositories("com.jason.rall.vtube.repository")
@Import(value = MongoAutoConfiguration.class)
public class DatabaseConfig {

    @Bean
    public ValidatingMongoEventListener validatingMongoEventListener() {
        return new ValidatingMongoEventListener(validator());
    }

    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }

    @Bean
    public Mongobee mongobee(MongoClient mongoClient, MongoTemplate mongoTemplate, MongoProperties mongoProperties) {
        Mongobee mongobee = new Mongobee(mongoClient);
        mongobee.setDbName(mongoProperties.getDatabase());
        mongobee.setMongoTemplate(mongoTemplate);
        // package to scan for migrations
        mongobee.setChangeLogsScanPackage("com.jason.rall.vtube.config.migration");
        mongobee.setEnabled(true);
        return mongobee;
    }
}
