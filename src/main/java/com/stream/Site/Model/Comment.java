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
    private String content;
    private String authorId;
    private Integer likes;
    private Integer dislikes;

    public Comment(String content, String author) {
        this.content = content;
        this.authorId = author;
    }
}
