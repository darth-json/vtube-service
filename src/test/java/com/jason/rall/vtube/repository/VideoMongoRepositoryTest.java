package com.jason.rall.vtube.repository;

import com.jason.rall.vtube.VtubeTest;
import com.jason.rall.vtube.domain.Video;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * Created by jasonrall on 4/23/18.
 */
public class VideoMongoRepositoryTest extends VtubeTest {

    @Autowired
    private VideoMongoRepository repository;

    @Before
    public void setUp() {
        repository.save(Video.builder().bitrate("foo:bits per second").fileName("foo.bar.mp4").build());
    }

        @Test
    public void findById() throws Exception {

    }

}