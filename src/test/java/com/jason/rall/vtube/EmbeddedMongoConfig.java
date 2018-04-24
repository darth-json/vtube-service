package com.jason.rall.vtube;

import com.mongodb.Mongo;
import cz.jirutka.spring.embedmongo.EmbeddedMongoBuilder;
import de.flapdoodle.embed.mongo.distribution.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.io.IOException;

/**
 * @author jrall
 */

@Configuration
@Profile("mongo.embed")
@EnableMongoRepositories("com.jason.rall.vtube.repository")
@EnableMongoAuditing
public class EmbeddedMongoConfig {
    private static final Logger log = LoggerFactory.getLogger(EmbeddedMongoConfig.class);
    private static final int DB_PORT = 12345;
    private static final String DB_HOST = "localhost";
    private static final String DB_NAME = "VtubeTest";

    @Bean(destroyMethod="close")
    public Mongo mongo() throws IOException {
        log.error("Creating new embeded mongo ");
        Mongo mongo = new EmbeddedMongoBuilder()
                .version(Version.V3_6_2)
                .bindIp(DB_HOST)
                .port(DB_PORT)
                .build();
        return mongo;
    }

}
