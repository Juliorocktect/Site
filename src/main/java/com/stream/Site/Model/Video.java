package com.stream.Site.Model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

@Document("videos")
@Data
@Component
@Getter
@Setter
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

    public Video(String title, String description, String userId,String videoUrl, String thumbnailUrl) {
        this.title = title;
        this.description = description;
        this.userId = userId;
        this.videoUrl = videoUrl;
        this.thumbnailUrl = thumbnailUrl;
    }
}
