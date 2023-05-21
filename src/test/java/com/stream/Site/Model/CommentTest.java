package com.stream.Site.Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CommentTest {
    @Test
    void likeTest() {
        Comment comment = new Comment("hallo", "123", "55344555");
        comment.like();
        assertEquals(1, comment.getLikes());
    }

    @Test
    void dislikeTest() {
        Comment comment = new Comment("hallo", "123", "55344555");
        comment.dislike();
        assertEquals(1, comment.getDislikes());
    }

    @Test
    void removeDislikeTest() {
        Comment comment = new Comment("hallo", "123", "55344555");
        comment.dislike();
        comment.dislike();
        comment.removeDislike();
        assertEquals(1, comment.getDislikes());
    }

    @Test
    void removeLikesTest() {
        Comment comment = new Comment("hallo", "123", "55344555");
        comment.like();
        comment.like();
        comment.like();
        comment.removeLike();
        assertEquals(2, comment.getLikes());
    }

}
