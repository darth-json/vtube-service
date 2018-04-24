package com.jason.rall.vtube.service;

import com.jason.rall.vtube.domain.Video;
import com.jason.rall.vtube.repository.VideoMongoRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * @author jrall
 */
public class VideoUploadServiceTest {

    @Mock
    VideoMongoRepository repository;

    @InjectMocks
    VideoUploadService videoService;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        videoService = new VideoUploadService(repository);
    }

    @Test
    public void createVideo() throws Exception {
        Path saveVideo = Paths.get("test-data/short.m4v");
        Video v1 = Video.builder()
                .id("foo-bar-baz-bss")
                .fileName("foo.bar")
                .bitrate("foo")
                .userId("asdf")
                .build();
         when(repository.save(any(Video.class))).thenReturn(v1);
        Optional<Video> v2 = videoService.createVideo(saveVideo);
        assertTrue(v2.isPresent());
        assertEquals(v2.get().getId(), v1.getId());
    }

    @Test
    public void getVideo() throws Exception {
        Video v1 = Video.builder()
                .id("foo-bar-baz-bss")
                .fileName("foo.bar")
                .bitrate("foo")
                .path("test-data/short.m4v")
                .userId("asdf")
                .build();
        when(repository.findById(anyString())).thenReturn(Optional.ofNullable(v1));
        Optional<Video> v2 = videoService.getVideo("foo-bar-baz-bss");
        assertTrue(v2.isPresent());
        assertEquals(v2.get().getId(), v1.getId());
    }

    @Test
    public void deleteVideo() throws Exception {
        Path testPath = Paths.get("src/test/resources/test-data/test.m4v");
        Files.copy(Paths.get("src/test/resources/test-data/short.m4v"),
                testPath);
        Video v1 = Video.builder()
                .id("foo-bar-baz-bss")
                .fileName("foo.bar")
                .bitrate("foo")
                .path(testPath.toString())
                .userId("asdf")
                .build();
        when(repository.findById(anyString())).thenReturn(Optional.ofNullable(v1));
        videoService.deleteVideo("doesn't-matter-because-mocks");
        assertFalse(Files.exists(testPath)); //verify that the file has been deleted
    }
}