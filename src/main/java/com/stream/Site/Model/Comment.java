package com.stream.Site.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@Data
public class Comment {
    @Id
    private String id;
    private String content;
    private String authorId;
    private Integer likes;
    private Integer dislikes;

    public Comment(String content, String authorId) {
        this.content = content;
        this.authorId = authorId;
    }

    public void like(){
        //TODO: add that user can only like once or dislike once
        likes++;
    }
    public void dislike(){
        dislikes--;
    }
}
