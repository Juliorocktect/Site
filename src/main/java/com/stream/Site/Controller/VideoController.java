package com.stream.Site.Controller;

import com.stream.Site.Model.User;
import com.stream.Site.Model.Video;
import com.stream.Site.Repository.VideoRepo;
import com.stream.Site.Service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class VideoController {

    @Autowired
    private VideoService service;

    @RequestMapping(
            value = "/createNewVideo",
            params = {
                    "title",
                    "description",
                    "userId",
                    "videoUrl",
                    "thumbnailUrl",
            },
            method = POST)
    public HttpStatus createVideo(@RequestParam("title") String title,
                                  @RequestParam("description") String description,
                                  @RequestParam("userId") String userId,
                                  @RequestParam("videoUrl") String videoUrl,
                                  @RequestParam("thumbnailUrl") String thumbnailUrl)
    {
        if(service.createNewVideo(title,description,userId,videoUrl,thumbnailUrl)){
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
                    }
    )
    public HttpStatus addComment(@RequestParam("id") String id,
                                 @RequestParam("content") String content,
                                 @RequestParam("authorId") String authorId){
        if(service.addComment(id,content,authorId)){
            return HttpStatus.OK;
        }
        return HttpStatus.BAD_REQUEST;
    }

    @GetMapping("/getAllVideos")
    public List<Video> getAllUsers(){
        return service.getAllVideos();
    }

    @RequestMapping(value = "/getVideoPerId/{id}", method = GET)
    @ResponseBody
    public Optional<Video> getVideoPerId(@PathVariable String id){
        return service.getVideoPerId(id);
    }

    @RequestMapping(value = "/getThumbnail/{id}",method = GET)
    public String getThumbnail(@PathVariable String id){
        return service.getThumbnail(id);
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
