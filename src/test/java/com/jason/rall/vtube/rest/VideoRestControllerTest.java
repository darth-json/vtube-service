package com.jason.rall.vtube.rest;

import com.jason.rall.vtube.domain.Video;
import com.jason.rall.vtube.service.VideoUploadService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.exceptions.ExceptionIncludingMockitoWarnings;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 *
 * @author jrall
 */
public class VideoRestControllerTest {

    private static final String SHORT_M4V = "test-data/short.m4v";

    private static final String FAIL_WHALE = "test-data/failwhale.gif";

    MockMvc mvc;

    @Mock
    VideoUploadService videoUploadService;

    @InjectMocks
    VideoRestController controller;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        controller = new VideoRestController(videoUploadService);
        mvc = MockMvcBuilders.standaloneSetup(controller)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .build();
    }

    @Test
    public void testMp4Upload() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "short_video_with_a_long_file_name_with_lots_and_lots_of_underscores.mp4", "audio/mp4", Files.newInputStream(Paths.get(ClassLoader.getSystemResource(SHORT_M4V).toURI())) );
        Video mockResponse = Video.builder().fileName("short_video_with_a_long_file_name_with_lots_and_lots_of_underscores").build();
        when(videoUploadService.createVideo(any())).thenReturn(Optional.ofNullable(mockResponse));
        mvc.perform(MockMvcRequestBuilders.multipart("/api/v1/video/").file(file))
                .andExpect(status().is(200))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testMp4NotMp4() throws Exception {
        MockMultipartFile failFile = new MockMultipartFile("data", "fail.mp4", "audio/mp4", "some text".getBytes());
        mvc.perform(MockMvcRequestBuilders.multipart("/api/v1/video/").file(failFile))
                .andExpect(status().is(400));
    }
}