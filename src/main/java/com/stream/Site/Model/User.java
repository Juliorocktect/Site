package com.stream.Site.Model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;


import java.util.List;
@Getter
@Setter
@RestController
@Component
@Data
@Document("users")
public class User {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String userName;
    private String pictureUrl;
    private List<String> subscribedToUsers;
    private List<String> subscribers;
    private List<String> videoHistory;
    private List<String> likedVideos;
    private List<String> dislikedVideos;

    public User(String firstName, String lastName, String userName, String pictureUrl) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.pictureUrl = pictureUrl;
    }
    @PostMapping("/createNewUser")
    private ResponseEntity<?> createNewUser(@RequestParam String firstName, @RequestParam String lastName,@RequestParam String userName,@RequestParam String pictureUrl){
        User user = new User(firstName, lastName, userName, pictureUrl);
        return ResponseEntity.ok("success");
    }
    public void addToSubscribedToUsers(String userId){
        subscribedToUsers.add(userId);
    }
    public void removeFromSubscribedToUsers(String userId){
        subscribedToUsers.remove(userId);
    }
    public void addSubscribedTo(String userId){
        subscribers.add(userId);
    }
    public void addToDislikedVideos(String videoId){
        dislikedVideos.add(videoId);
    }
    public void addToLikedVideos(String videoId){
        likedVideos.add(videoId);
    }
    public void removeFromDislikedVideos(String videoId){
        dislikedVideos.remove(videoId);
    }
    public void addToHistory(String videoId){
        videoHistory.add(videoId);
    }


}
