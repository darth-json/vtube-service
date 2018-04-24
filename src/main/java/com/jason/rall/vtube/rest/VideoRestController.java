package com.jason.rall.vtube.rest;

import com.jason.rall.vtube.domain.Video;
import com.jason.rall.vtube.service.VideoUploadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    @PostMapping(value = "/"    )
    @ResponseBody
    public ResponseEntity<Video> uploadVideo(@RequestPart(value="file") MultipartFile multipartFile) throws IOException {
        Path file = convertMultiPartToFile(multipartFile);
        Optional<Video> video = videoUploadService.createVideo(file);
        if(video.isPresent()) return ResponseEntity.ok(video.get());
        else return ResponseEntity.unprocessableEntity().build();
    }

    /**
     *
     * @param videoId, the UUID of a given video
     * @return returns the metadata of a given video
     */
    @GetMapping(value = "/{videoId}")
    @ResponseBody
    public ResponseEntity<Video> getVideo(@PathVariable String videoId) {
        Optional<Video> video = videoUploadService.getVideo(videoId);
        if(video.isPresent()) return ResponseEntity.ok(video.get());
        else return ResponseEntity.unprocessableEntity().build();
    }

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
