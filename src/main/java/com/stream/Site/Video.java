package com.stream.Site;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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

    public User user;

    @PostMapping("/like")
    public void like(){
        this.likes = this.likes+1;
    }

    
    public void dislike(String Id){
        List<String> likedVideos = user.getLikedVideos();
        if(likedVideos.contains(userId)){
            this.dislikes=this.dislikes+1;
        }
    }
    public String getThumbnailUrl() {
        return thumbnailUrl;
    }
    public List<Comment> getComments() {
        return comments;
    }
    public String getDescription() {
        return description;
    }
    public Integer getDislikes() {
        return dislikes;
    }
    public String getId() {
        return Id;
    }
    public Integer getLikes() {
        return likes;
    }
    public String getTitle() {
        return title;
    }
    public Integer getViews() {
        return views;
    }
    public String getUserId() {
        return userId;
    }
    public String getVideoUrl() {
        return videoUrl;
    }

    
}
