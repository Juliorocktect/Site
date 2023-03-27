package com.stream.Site;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class Comment {
    private String id;
    private String content;
    private String authorId;
    private Integer likes;
    private Integer dislikes;

    public Comment(String content, String author, Integer likes, Integer dislikes) {
        this.content = content;
        this.authorId = author;
        this.likes = likes;
        this.dislikes = dislikes;
    }
}
