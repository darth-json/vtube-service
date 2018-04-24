package com.jason.rall.vtube.service;

import com.jason.rall.vtube.domain.Video;
import com.jason.rall.vtube.repository.VideoMongoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

/**
 *
 * @author jrall
 */
@Service
public class VideoUploadService {

    private static final Logger log = LoggerFactory.getLogger(VideoUploadService.class);

    private static VideoMongoRepository videoMongoRepository;

    public VideoUploadService(VideoMongoRepository videoMongoRepository) {
        this.videoMongoRepository = videoMongoRepository;
    }

    public Optional<Video> createVideo(@NonNull Path path) {
        Video video = Video.builder()
                .path(path.toAbsolutePath().toString())
                .fileName(path.getFileName().toString()).build();
        return Optional.of(videoMongoRepository.save(video));
    }

    public Optional<Video> getVideo(@NonNull String videoId) {
        Optional<Video> video = videoMongoRepository.findById(videoId);
        return video;
    }

    public void deleteVideo(@NonNull String videoId) throws IOException {
        Optional<Video> deleteVideo = videoMongoRepository.findById(videoId);
        if(deleteVideo.isPresent() && Files.exists(Paths.get(deleteVideo.get().getPath()))) Files.delete(Paths.get(deleteVideo.get().getPath()));
        if(deleteVideo.isPresent()) videoMongoRepository.delete(deleteVideo.get());
    }
}
