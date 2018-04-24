package com.jason.rall.vtube.repository;

import com.jason.rall.vtube.VtubeTest;
import com.jason.rall.vtube.domain.Video;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.util.Optional;

/**
 *
 */

public class VideoMongoRepositoryTest extends VtubeTest {

    @Autowired
    private VideoMongoRepository repository;

    @Test
    public void testFindById() {
        Video v = Video.builder()
                .fileName("foo.bar")
                .bitrate("foo")
                .userId("asdf")
                .build();
        v = repository.save(v);
        Optional<Video> v2 = repository.findById(v.getId());
        assertNotNull(v2.get());
        assertEquals(v2.get().getBitrate(), v.getBitrate());
        assertEquals(v2.get().getFileName(), v.getFileName());
    }
}
