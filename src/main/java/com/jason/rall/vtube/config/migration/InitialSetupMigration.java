package com.jason.rall.vtube.config.migration;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import com.jason.rall.vtube.domain.UserDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * @author jrall
 */
@ChangeLog(order = "001")
public class InitialSetupMigration {

    private static final Logger log = LoggerFactory.getLogger(InitialSetupMigration.class);

    @ChangeSet(order = "01", author = "initiator", id = "01-addUsers")
    public void addUsers(MongoTemplate mongoTemplate) {
        log.info("Creating users");
        mongoTemplate.save(new UserDetail("jrall","test"));
        mongoTemplate.save(new UserDetail("demo","demo"));
        mongoTemplate.save(new UserDetail("admin","admin"));
    }
}
