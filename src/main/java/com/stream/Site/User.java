package com.stream.Site;

import org.springframework.data.mongodb.core.aggregation.ArrayOperators;

import java.util.List;

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

}
