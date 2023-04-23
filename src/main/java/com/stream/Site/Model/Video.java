package com.stream.Site.Model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.stereotype.Component;

@Document("videos")
@Data
@Component
@Getter
@Setter
public class Video {

    @Id
    private String id;
    private String title;
    private String description;
    private String userId;
    private String profilePicture;
    private Integer likes;
    private Integer dislikes;
    private String videoUrl;
    private double rating;
    private Content videoData;
    private Integer views;
    private String thumbnailUrl;
    private Content thumbnailData;
    private LocalDateTime uplaoadDate;
    private List<Comment> comments;

    public Video(String title, String description, String userId) {
        this.title = title;
        this.description = description;
        this.userId = userId;
        List<Comment> commentList = new ArrayList<>();
        this.comments = commentList;
        this.likes = 0;
        this.dislikes = 0;
        this.profilePicture = "null";
        this.uplaoadDate = LocalDateTime.now();
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public void removeComment(String authorId) {
        List<Comment> commentList = getComments();
        List<Comment> collected = commentList.stream().filter(p -> p.getAuthorId().equals(authorId)).collect(Collectors.toList());
        comments.remove(collected);
    }

    public void like() {
        likes++;
    }
    public void view(){views++;}
}
