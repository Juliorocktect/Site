package com.stream.Site.Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VideoTest {

    @Test
    void addComment() {
        Video video = new Video("Hallo", "1243dfg", "123123");
        Comment newComment = new Comment("123", "11231231", "653453");
        video.addComment(newComment);
        assertEquals(newComment, video.getComments().get(0));
    }

    @Test
    void removeComment() {
        Video video = new Video("Hallo", "1243dfg", "123123");
        Comment newComment = new Comment("123", "11231231", "653453");
        video.addComment(newComment);
        video.removeComment("11231231");
        assertEquals(true, video.getComments().isEmpty());
    }

    @Test
    void likeTest() {
        Video video = new Video("Hallo", "1243dfg", "123123");
        video.like();
        assertEquals(1, video.getLikes());
    }

    @Test
    void viewTest() {
        Video video = new Video("Hallo", "1243dfg", "123123");
        video.view();
        assertEquals(1, video.getViews());
    }
}