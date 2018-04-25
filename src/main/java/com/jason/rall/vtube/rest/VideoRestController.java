package com.jason.rall.vtube.rest;

import com.jason.rall.vtube.domain.Video;
import com.jason.rall.vtube.service.VideoUploadService;
import com.jason.rall.vtube.utils.MP4Utils;
import org.mp4parser.boxes.iso14496.part12.MovieHeaderBox;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author jrall
 */
@RestController
@RequestMapping("/api/v1/video")
public class VideoRestController {

    private static VideoUploadService videoUploadService;

    private static String uploadTempFolder;

    public VideoRestController(VideoUploadService videoUploadService, String uploadTempFolder){
        this.videoUploadService = videoUploadService;
        this.uploadTempFolder = uploadTempFolder;
    }

    /**
     * Takes a file as an upload
     *
     * @param multipartFile, the video file
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/" , consumes = "multipart/form-data")
    public ResponseEntity<Video> uploadVideo(@RequestParam(value="file") MultipartFile multipartFile) throws IOException {
        if(("video/mp4").equalsIgnoreCase(multipartFile.getContentType())) {
            Path file = convertMultiPartToFile(multipartFile);
            //Kind of a pointless check as I also have limits on the file-size itself
            //but if there's an mp4 of low-enough quality maybe
            if(MP4Utils.parseMetadata(file).getDuration() > 600000) return new ResponseEntity(HttpStatus.PAYLOAD_TOO_LARGE);
            Optional<Video> video = videoUploadService.createVideo(file);
            if (video.isPresent()) return ResponseEntity.ok(video.get());
            else return ResponseEntity.unprocessableEntity().build();
        } else return new ResponseEntity<>(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    /**
     * Should migrate to a paging and sorting repository
     * and take in paging sorting options
     * @return
     */
    @GetMapping(value = "/")
    public ResponseEntity<List<Video>> getVideos() {
        Optional<List<Video>> videos = videoUploadService.getVideos();
        if(videos.isPresent()) return ResponseEntity.ok(videos.get());
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     *
     * @param videoId, the UUID of a given video
     * @return returns the metadata of a given video
     */
    @GetMapping(value = "/{videoId}")
    public ResponseEntity<Video> getVideo(@PathVariable String videoId) {
        Optional<Video> video = videoUploadService.getVideo(videoId);
        if(video.isPresent()) return ResponseEntity.ok(video.get());
        else return ResponseEntity.notFound().build();
    }

    /**
     * @param videoId
     * @return
     * @throws IOException
     */
    @DeleteMapping(value = "/{videoId}")
    public ResponseEntity<Void> deleteVideo(@PathVariable String videoId) throws IOException {
        videoUploadService.deleteVideo(videoId);
        return ResponseEntity.ok().build();
    }


    private Path convertMultiPartToFile(MultipartFile file) throws IOException {
        Path path = Paths.get(uploadTempFolder+ "/" +file.getOriginalFilename());
        if (!Files.exists(path.getParent())) Files.createDirectories(path.getParent());
        Files.write(path, file.getBytes());
        return path;
    }


}
