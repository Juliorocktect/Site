package com.stream.Site.Repository;

import com.stream.Site.Model.Video;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VideoRepo extends MongoRepository<Video, String> {
}
