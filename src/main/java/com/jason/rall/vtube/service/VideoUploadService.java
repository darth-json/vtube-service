package com.jason.rall.vtube.service;

import com.jason.rall.vtube.domain.Video;
import com.jason.rall.vtube.repository.VideoRepository;
import org.mp4parser.Box;
import org.mp4parser.IsoFile;
import org.mp4parser.boxes.iso14496.part12.MovieBox;
import org.mp4parser.boxes.iso14496.part12.MovieHeaderBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.channels.FileChannel;
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

    private static VideoRepository videoRepository;

    public VideoUploadService(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    private MovieHeaderBox parseMetadata(Path path) throws IOException {
        FileChannel fc = new FileInputStream(path.toFile()).getChannel();
        IsoFile isoFile = new IsoFile(fc);
        MovieBox moov = isoFile.getMovieBox();
        MovieHeaderBox header = moov.getMovieHeaderBox();
        return header;
    }

    public Optional<Video> createVideo(@NonNull Path path) throws IOException {
        MovieHeaderBox headers = parseMetadata(path);
        Video video = Video.builder()
                .path(path.toAbsolutePath().toString())
                .userId(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())
                .bitrate(String.valueOf(headers.getRate()))
                .duration(String.valueOf(headers.getDuration()))
                .fileName(path.getFileName().toString()).build();
        return Optional.of(videoRepository.save(video));
    }

    public Optional<Video> getVideo(@NonNull String videoId) {
        Optional<Video> video = videoRepository.findById(videoId);
        return video;
    }

    public void deleteVideo(@NonNull String videoId) throws IOException {
        Optional<Video> deleteVideo = videoRepository.findById(videoId);
        if(deleteVideo.isPresent() && Files.exists(Paths.get(deleteVideo.get().getPath()))) Files.delete(Paths.get(deleteVideo.get().getPath()));
        if(deleteVideo.isPresent()) videoRepository.delete(deleteVideo.get());
    }
}
