package com.stream.Site.Service;

import com.stream.Site.Model.Comment;
import com.stream.Site.Model.User;
import com.stream.Site.Repository.VideoRepo;
import com.stream.Site.Model.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Service
@RestController
public class VideoService {
    @Autowired
    private VideoRepo repo;
    @Autowired
    private UserService userService;

    public boolean createNewVideo( String title,
                                   String description,
                                   String userId,
                                   String videoUrl,
                                   String thumbnailUrl)
    {
        if(userService.checkIfUserExists(userId)) {
            ; //TODO: if statement
            Video video = new Video(title, description, userId, videoUrl, thumbnailUrl);
            repo.save(video);
            String videoId = video.getId();
            if (repo.findById(video.getId()).isPresent()) {
                return true;
            }
        }
        return false;
    }
    public List<Video> getAllVideos(){
        return repo.findAll();
    }
    public Optional<Video> getVideoPerId(String id){
        return repo.findById(id);
    }
    public String getThumbnail(String id){
        Optional<Video> videoPerId = getVideoPerId(id);
        if (videoPerId.isPresent()){
            return videoPerId.get().getThumbnailUrl();
        }
        return "no such Video with this id";
    }
    public String getVideoUrl(String id){
        Optional<Video> videoPerId = getVideoPerId(id);
        if(videoPerId.isPresent()){
            return videoPerId.get().getVideoUrl();
        }
        return "no such Video with this id";
    }

    public String getAuthor(String id) {
        Optional<Video> videoPerId = getVideoPerId(id);
        if(videoPerId.isPresent()){
            User userPerId = userService.getUserPerId(videoPerId.get().getUserId());
            return userPerId.getUserName();
        }
        return "There is no such user with this id";
    }
    public void setThumbnailUrl(String id, String Url){
        Optional<Video> videoPerId = getVideoPerId(id);
        if (videoPerId.isPresent()){
            videoPerId.get().setThumbnailUrl(Url);
        }
    }
    public void setVideoUrl(String id, String Url){
        Optional<Video> videoPerId = getVideoPerId(id);
        if (videoPerId.isPresent()){
            videoPerId.get().setVideoUrl(Url);
        }
    }

    public boolean addComment(String id,String content,String authorId){
        Comment comment = new Comment(content,authorId);
        Optional<Video> videoPerId = getVideoPerId(id);
        if(videoPerId.isPresent()){
            videoPerId.get().getComments().add(comment);
            repo.save(videoPerId.get());
            return true;
        }
        return false;
    }

}
