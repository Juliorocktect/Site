package com.stream.Site.Repository;

import com.stream.Site.Video;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepo extends MongoRepository<Video,String> {
}
