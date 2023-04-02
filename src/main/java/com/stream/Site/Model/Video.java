package com.stream.Site.Model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletionException;
import java.util.stream.Collectors;

import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

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
        List<Comment> commentList =  new ArrayList<>();
        this.comments = commentList;
    }

    public void addComment(Comment comment){
        comments.add(comment);
    }
    public void removeComment(String authorId){
        List<Comment> commentList = getComments();
        List<Comment> collected = commentList.stream().filter(p -> p.getAuthorId().equals(authorId)).collect(Collectors.toList());
        comments.remove(collected);
    }
}
