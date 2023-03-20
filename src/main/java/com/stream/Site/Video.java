package com.stream.Site;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Video {
    private int likes;
    private int views;
    private int title;

    @GetMapping(value = "/like")
    public void like(){
        this.likes = this.likes+1;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
    @GetMapping(value = "/getLike")
    public int getLikes() {
        return likes;
    }

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public int getViews() {
        return views;
    }
}
