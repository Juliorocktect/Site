package com.stream.Site;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(value = "Video")
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

}
