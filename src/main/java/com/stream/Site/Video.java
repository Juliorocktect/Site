package com.stream.Site;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Document
@Data
@Component
public class Video {

    @Id
    private String Id;
    private String title;
    private String description;
    private String userId;
    private Integer likes;
    private Integer dislikes;
    private String videoUrl;
    private Integer views;
    private String thumbnailUrl;
    private List<Comment> comments;

    public User user;

    public Video(String title, String description, String userId,String videoUrl, String thumbnailUrl) {
        this.title = title;
        this.description = description;
        this.userId = userId;
        this.videoUrl = videoUrl;
        this.thumbnailUrl = thumbnailUrl;
    }

    @PostMapping("/dislike")
    public void dislike(String Id) {
        List<String> likedVideos = user.getLikedVideos();
        if (likedVideos.contains(userId)) {
            this.dislikes = this.dislikes + 1;
        }
    }

    
}
