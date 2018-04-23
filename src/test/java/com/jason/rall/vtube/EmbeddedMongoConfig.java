package com.jason.rall.vtube;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import cz.jirutka.spring.embedmongo.EmbeddedMongoBuilder;
import de.flapdoodle.embed.mongo.distribution.Version;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.io.IOException;

/**
 * @author jrall
 */


public class EmbeddedMongoConfig {
    private static final int DB_PORT = 12345;
    private static final String DB_HOST = "127.0.0.1";
    private static final String DB_NAME = "test";

    @Bean(destroyMethod="close")
    public Mongo mongo() throws IOException {
        return new EmbeddedMongoBuilder()
                .version(Version.V3_6_2)
                .bindIp(DB_HOST)
                .port(DB_PORT)
                .build();
    }

}
