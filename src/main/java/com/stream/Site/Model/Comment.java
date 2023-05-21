package com.stream.Site.Model;

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
    private String videoId;

    private String content;
    private String authorId;
    private Integer likes;
    private Integer dislikes;

    public Comment(String content, String authorId, String videoId) {
        this.content = content;
        this.authorId = authorId;
        this.videoId = videoId;
        this.dislikes = 0;
        this.likes = 0;
    }

    public void like() {
        likes++;
    }

    public void removeLike() {
        likes--;
    }

    public void dislike() {
        dislikes++;
    }

    public void removeDislike() {
        dislikes--;
    }
}
