package com.jason.rall.vtube.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.File;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * @author jrall
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "videos")
public class Video {

    @Id
    @Field("id")
    private String id;

    @Field("user_id")
    private String userId;

    @Field("file_name")
    private String fileName;

    @Field("s3file_name")
    private String s3FileName;

    private File file;

    @Field("bitrate")
    private String bitrate;

    @Field("duration")
    private String duration;

}
