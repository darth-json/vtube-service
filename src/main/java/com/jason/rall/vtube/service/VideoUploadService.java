package com.jason.rall.vtube.service;

import com.amazonaws.services.s3.AmazonS3;
import com.jason.rall.vtube.domain.Video;
import com.jason.rall.vtube.repository.VideoMongoRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import java.io.File;
import java.util.Optional;

/**
 *
 * @author jrall
 */
@Service
public class VideoUploadService {

    private static VideoMongoRepository videoMongoRepository;

    private static AmazonS3 amazonS3;


    public VideoUploadService(VideoMongoRepository videoMongoRepository, AmazonS3 amazonS3) {
        this.videoMongoRepository = videoMongoRepository;
        this.amazonS3 = amazonS3;
    }

    public Optional<Video> createVideo(@NonNull  File file) {
        Video video = Video.builder().fileName(file.getName()).build();
        return Optional.ofNullable(video);
    }
}
