package com.stream.Site.Model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@Component
@Data
@Document("users")
public class User {
    @Id
    private String id;
    private String passWord;
    private String firstName;
    private String lastName;
    private String userName;
    private String pictureUrl;
    private String bannerUrl;
    private List<String> subscribedToUsers;
    private List<String> subscribers;
    private List<String> videoHistory;
    private List<String> likedVideos;
    private List<String> dislikedVideos;

    public User(String passWord,String firstName, String lastName, String userName, String pictureUrl,String bannerUrl) {
        this.passWord = passWord;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.pictureUrl = pictureUrl;
        this.bannerUrl = bannerUrl;
        this.subscribedToUsers = new ArrayList<>();
        this.videoHistory = new ArrayList<>();
        this.subscribers = new ArrayList<>();
        this.likedVideos = new ArrayList<>();
        this.dislikedVideos = new ArrayList<>();
    }

    public void addToSubscribedToUsers(String userId) {
        subscribedToUsers.add(userId);
    }

    public void removeFromSubscribedToUsers(String userId) {
        subscribedToUsers.remove(userId);
    }

    public void addSubscriber(String userId) {
        subscribers.add(userId);
    }

    public void removeSubscriber(String userId) {
        subscribers.remove(userId);
    }

    public void addToDislikedVideos(String videoId) {
        dislikedVideos.add(videoId);
    }

    public void addToLikedVideos(String videoId) {
        likedVideos.add(videoId);
    }

    public void removeFromDislikedVideos(String videoId) {
        dislikedVideos.remove(videoId);
    }

    public void removeFromLikedVideos(String videoId) {
        likedVideos.remove(videoId);
    }

    public void addToHistory(String videoId) {
        videoHistory.add(videoId);
    }

    public void removeFromHistory(String videoId) {
        videoHistory.remove(videoId);
    }


}
