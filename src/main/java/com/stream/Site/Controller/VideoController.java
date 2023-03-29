package com.stream.Site.Controller;

import com.stream.Site.Model.User;
import com.stream.Site.Model.Video;
import com.stream.Site.Repository.VideoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class VideoController {
    @Autowired
    private VideoRepo repository;

    @PostMapping("/addVideo")
    public String addVideo(){
        Video video = new Video("sui","sui","sui","sui","sui");
        repository.save(video);
        return "Added";
    }
    @GetMapping("/getAllVideos")
    public List<Video> getAllUsers(){
        return repository.findAll();
    }
    @RequestMapping(value = "/getVideoPerId/{id}", method = GET)
    @ResponseBody
    public Optional<Video> getVideoPerId(@PathVariable String id){
        return repository.findById(id);
    }
    @RequestMapping(value = "/getThumbnail/{id}",method = GET)
    public String getThumbnail(@PathVariable String id){
        Optional<Video> videoPerId = getVideoPerId(id);
        return videoPerId.get().getThumbnailUrl();
    }



}
