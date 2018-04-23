package com.jason.rall.vtube.repository;

import com.jason.rall.vtube.domain.Video;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jrall
 */
@Repository
public interface VideoMongoRepository extends MongoRepository<Video, String> {

}
