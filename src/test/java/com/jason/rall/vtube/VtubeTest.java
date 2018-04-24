package com.jason.rall.vtube;

import com.jason.rall.vtube.config.DatabaseConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(value =  {"mongo.embed"})
@ContextConfiguration(classes = {EmbeddedMongoConfig.class, DatabaseConfig.class})
public abstract class VtubeTest {

}
