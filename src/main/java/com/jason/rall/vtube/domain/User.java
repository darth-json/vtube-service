package com.jason.rall.vtube.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.ZonedDateTime;

/**
 * @author jrall
 */
@Data
@Builder
@Document(collection = "users")
public class User {
    @Id
    private String id;

    @Field("user_name")
    private String userName;

    @Field("create_time")
    private ZonedDateTime createTime;
}
