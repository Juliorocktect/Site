package com.stream.Site.Controller;

import com.mongodb.lang.Nullable;
import com.stream.Site.Model.Comment;
import com.stream.Site.Model.Video;
import com.stream.Site.Service.VideoService;
import jakarta.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;
import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
@EnableWebMvc
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class VideoController {

    @Autowired
    private VideoService service;
    @Autowired
    ServletContext context;

    @RequestMapping(
            value = "/createNewVideo",
            params = {
                    "title",
                    "description",
                    "userId",
            },
            method = POST)
    public String createVideo(@RequestParam("title") String title,
                                  @RequestParam("description") String description,
                                  @RequestParam("userId") String userId) {
        return service.createNewVideo(title,description,userId);
    }

    @PostMapping("/addComment") 
    public HttpStatus addComment(@RequestParam("videoId") String videoId, @RequestParam("content") String content, @RequestParam("authorId") String authorId) {
        Comment comment = new Comment(content, authorId, videoId);
        if (service.addComment(comment)) {
            return HttpStatus.OK;
        } else {
            return HttpStatus.BAD_REQUEST;
        }
    }

    @GetMapping("/getTenVideos")
    public List<Video> getTenVideos() {
        return service.getTenVideos();
    }

    @GetMapping("/getVideoPerId/{id}")
    public Optional<Video> getVideoPerId(@PathVariable String id) {
        return service.getVideoPerId(id);
    }

    @RequestMapping(value = "/getThumbnail/{id}", method = GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ResponseBody
    public String getThumbnailUrl(@PathVariable String id) {
        return service.getThumbnailUrlPerId(id);
    }

    @RequestMapping(value = "/getVideo/{id}", method = GET)
    public String getVideo(@PathVariable String id) {
        return service.getVideoUrl(id);
    }

    @RequestMapping(value = "/getAuthor/{id}", method = GET)
    public String getAuthor(@PathVariable String id) {
        return service.getAuthor(id);
    }

    @PostMapping("/like")
    public void like(@RequestParam String id, @Nullable @RequestParam String userId) {
        service.like(id,userId);
    }

    @PostMapping("/view")
    public HttpStatus view(@RequestParam @Nullable String userId, String videoId){
        if (service.view(userId,videoId)) {
            return HttpStatus.OK;
        }
        return HttpStatus.BAD_REQUEST;
    }

}
