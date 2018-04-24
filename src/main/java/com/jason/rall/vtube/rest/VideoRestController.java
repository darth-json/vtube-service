package com.jason.rall.vtube.rest;

import com.jason.rall.vtube.domain.Video;
import com.jason.rall.vtube.service.VideoUploadService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;

/**
 *
 * @author jrall
 */
@RestController
@RequestMapping("/api/v1/video")
public class VideoRestController {

    private static VideoUploadService videoUploadService;

    public VideoRestController(VideoUploadService videoUploadService){
        this.videoUploadService = videoUploadService;
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
    public Video uploadVideo(@RequestPart(value="file") MultipartFile multipartFile) throws IOException {
        File file = convertMultiPartToFile(multipartFile);
        Optional<Video> video = videoUploadService.createVideo(file);
        if(video.isPresent()) return video.get();
        else return null;
    }

    /**
     *
     * @param videoId, the UUID of a given video
     * @return returns the metadata of a given video
     */
    @GetMapping(value = "/{videoId}")
    @ResponseBody
    public Video getVideo(@PathVariable String videoId) {
        return null;
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }


}
