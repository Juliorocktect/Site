package com.stream.Site.Service;

import com.stream.Site.Repository.VideoRepo;
import com.stream.Site.Model.Video;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Service
@RestController
public class VideoService {
    private VideoRepo repo;

    public void likeVideo(String videoId){
        //Video videoById = getVideoById;
    }
    @RequestMapping(
            value = "/createNewVideo",
            params = {"title",
                    "description",
                    "userId",
                    "videoUrl",
                    "thumbnailUrl",
            },
            method = POST)
    public HttpStatus createVideo(@RequestParam("title") String title,@RequestParam("description") String description,@RequestParam("userId") String userId,@RequestParam("videoUrl") String videoUrl,@RequestParam("thumbnailUrl") String thumnailUrl){
        Video video = new Video(title,description,userId,videoUrl,thumnailUrl);
        repo.insert(video);
        String videoId = video.getId();
        if (repo.findById(video.getId()).isPresent()){
            return HttpStatus.OK;
        }
        return HttpStatus.BAD_REQUEST;
    }

}
