package com.stream.Site.Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void addToSubscribedToUsers() {
        User newUser = new User("1234", "123", "3331dfrg", "sdf", "sdfgsd", "sdfsdf");
        newUser.addToSubscribedToUsers("123");
        assertEquals("123", newUser.getSubscribedToUsers().get(0));
    }

    @Test
    void removeFromSubscribedToUsers() {
        User newUser = new User("1234", "123", "3331dfrg", "sdf", "sdfgsd", "sdfsdf");
        newUser.addToSubscribedToUsers("123");
        newUser.removeFromSubscribedToUsers("123");
        assertEquals(true, newUser.getSubscribedToUsers().isEmpty());
    }

    @Test
    void addSubscribedTo() {
        User newUser = new User("1234", "123", "3331dfrg", "sdf", "sdfgsd", "sdfsdf");
        newUser.addSubscriber("123");
        assertEquals("123", newUser.getSubscribers().get(0));
    }

    @Test
    void addToDislikedVideos() {
        User newUser = new User("1234", "123", "3331dfrg", "sdf", "sdfgsd", "sdfsdf");
        newUser.addToDislikedVideos("1234");
        newUser.addToDislikedVideos("123");
        assertEquals("123", newUser.getDislikedVideos().get(newUser.getDislikedVideos().size() - 1));
    }

    @Test
    void addToLikedVideos() {
        User newUser = new User("1234", "123", "3331dfrg", "sdf", "sdfgsd", "sdfsdf");
        newUser.addToLikedVideos("1235");
        newUser.addToLikedVideos("1234");
        assertEquals("1234", newUser.getLikedVideos().get(newUser.getLikedVideos().size() - 1));
    }

    @Test
    void removeFromDislikedVideos() {
        User newUser = new User("1234", "123", "3331dfrg", "sdf", "sdfgsd", "sdfsdf");
        newUser.addToDislikedVideos("1235");
        newUser.removeFromDislikedVideos("1235");
        assertEquals(true, newUser.getDislikedVideos().isEmpty());

    }

    @Test
    void addToHistory() {
        User newUser = new User("1234", "123", "3331dfrg", "sdf", "sdfgsd", "sdfsdf");
        newUser.addToHistory("12345");
        assertEquals("12345", newUser.getVideoHistory().get(0));
    }

    @Test
    void removeSubscriber() {
        User newUser = new User("1234", "123", "3331dfrg", "sdf", "sdfgsd", "sdfsdf");
        newUser.addSubscriber("123");
        newUser.removeSubscriber("123");
        assertEquals(true, newUser.getSubscribers().isEmpty());
    }

    @Test
    void removeFromHistory() {
        User newUser = new User("1234", "123", "3331dfrg", "sdf", "sdfgsd", "sdfsdf");
        newUser.addToHistory("1243");
        newUser.removeFromHistory("1243");
        assertEquals(true, newUser.getVideoHistory().isEmpty());
    }
}