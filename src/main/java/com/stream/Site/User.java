package com.stream.Site;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.lang3.RandomStringUtils;


import java.util.List;
@Getter
@Setter
@RestController
@Component
@Document("users")
public class User {
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
    public List<User> allUsers;

    public User(String firstName, String lastName, String userName, String pictureUrl) {
        this.id = RandomStringUtils.randomAlphanumeric(10);
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.pictureUrl = pictureUrl;
    }
    @PostMapping("/createNewUser")
    private ResponseEntity<?> createNewUser(@RequestParam String firstName, @RequestParam String lastName,@RequestParam String userName,@RequestParam String pictureUrl){
        User user = new User(firstName, lastName, userName, pictureUrl);
        this.allUsers.add(user);
        return ResponseEntity.ok("success");
    }
    @GetMapping("/getAllUsers")
    public List<User> getAllUsers() {
        return allUsers;
    }
    public List<String> getLikedVideos() {
        return likedVideos;
    }

    @GetMapping("/getUserPerId")
    public User getUserPerId(String Id){
        List<User> allUsers = getAllUsers();
            List<User> sortedList = allUsers.stream().filter(c -> c.allUsers.contains(allUsers.indexOf(id))).toList();
            User user = sortedList.get(0);
        return user;
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
