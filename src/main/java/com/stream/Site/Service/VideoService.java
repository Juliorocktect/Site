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
import java.util.StringTokenizer;
import java.util.stream.Collectors;

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
        Optional<Video> videoPerId = getVideoPerId(id);
        if(videoPerId.isPresent()){ //TODO: check if user exists
            Video video = videoPerId.get();
            video.addComment(content,authorId);
            repo.save(video);
            return true;
        }
        return false;
    }
    public boolean removeComment(String videoId, String authorId){
        Optional<Video> videoPerId = getVideoPerId(videoId);
        if(videoPerId.isPresent()){
            videoPerId.get().removeComment(authorId);
            if(checkIfCommentExists(videoId,authorId)){
                return false;
            }
        }
        return false;
    }

    public boolean checkIfCommentExists(String videoId ,String authorId){
        Optional<Video> videoPerId = getVideoPerId(videoId);
        if (videoPerId.isPresent()){
            List<Comment> commentList = videoPerId.get().getComments();
            List<Comment> collect = commentList.stream().filter(p -> p.getAuthorId().equals(authorId)).toList();
            if (!collect.isEmpty()){
                return true;
            }
            else {
                return false;
            }
        }
        return false;
    }
    public boolean addLikeToComment(String commentId){
        //fill
        return false;
    }
    public boolean addDislikeToComment(String commentId){
        //fill+
        return false;
    }

}
