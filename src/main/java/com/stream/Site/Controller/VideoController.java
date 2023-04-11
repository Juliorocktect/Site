package com.stream.Site.Controller;

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
    public HttpStatus createVideo(@RequestParam("title") String title,
                                  @RequestParam("description") String description,
                                  @RequestParam("userId") String userId)
    {
        if(service.createNewVideo(title,description,userId)){
            return HttpStatus.OK;
        }
        return HttpStatus.BAD_REQUEST;
    }

    @RequestMapping(
            value = "/addComment",
            params = {
                    "id",
                    "content",
                    "authorId",
                    },
            method = POST
    )
    public HttpStatus addComment(@RequestBody Comment comment){
        if(service.addComment(comment)){
            return HttpStatus.OK;
        }
        else {
            return HttpStatus.BAD_REQUEST;
        }
    }

    @GetMapping("/getTenVideos")
    public List<Video> getTenVideos(){
        return service.getTenVideos();
    }

    @RequestMapping(value = "/getVideoPerId", method = GET)
    public Optional<Video> getVideoPerId(@RequestParam String id){
        return service.getVideoPerId(id);
    }

    @RequestMapping(value = "/getThumbnail/{id}",method = GET,produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ResponseBody
    public String getThumbnailUrl(@PathVariable String id){
        return service.getThumbnailUrlPerId(id);
    }

    @RequestMapping(value = "/getVideo/{id}",method = GET)
    public String getVideo(@PathVariable String id){
        return service.getVideoUrl(id);
    }

    @RequestMapping(value = "/getAuthor/{id}",method = GET)
    public String getAuthor(@PathVariable String id){
        return service.getAuthor(id);
    }




}
