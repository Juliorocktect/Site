package com.stream.Site.Service;

import com.stream.Site.Model.*;
import com.stream.Site.Repository.VideoRepo;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
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
                                   String userId)
    {
        if(userService.checkIfUserExists(userId)) {
            Video video = new Video(title, description, userId);
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
    public String  getThumbnailPath(String id){
        Optional<Video> videoPerId = getVideoPerId(id);
        if (videoPerId.isPresent()){
            String path = "classpath:videos/" + id +"/" + videoPerId.get().getThumbnailData().getName();
            return path;
        }
        return "bad";
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
            repo.save(videoPerId.get());
        }
    }
    public void setVideoData(String videoId,String name,String type,long bytes){
        Optional<Video> videoPerId = getVideoPerId(videoId);
        if (videoPerId.isPresent()){
            Content content = new Content(name, type, bytes);
            videoPerId.get().setVideoData(content);
            repo.save(videoPerId.get());
        }
    }
    public void setThumbnailData(String videoId,String name,String type,long bytes){
        Optional<Video> videoPerId = getVideoPerId(videoId);
        if (videoPerId.isPresent()){
            Content content = new Content(name, type, bytes);
            videoPerId.get().setThumbnailData(content);
            repo.save(videoPerId.get());
        }
    }
    public void setVideoUrl(String id, String Url){
        Optional<Video> videoPerId = getVideoPerId(id);
        if (videoPerId.isPresent()){
            videoPerId.get().setVideoUrl(Url);
            repo.save(videoPerId.get());
        }
    }

    public boolean addComment(Comment comment){
        Optional<Video> videoPerId = getVideoPerId(comment.getVideoId());
        if(videoPerId.isPresent()){ //TODO: check if user exists
            Video video = videoPerId.get();
            video.addComment(comment);
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
    public String getvideoUrlPerId(String id){
        Optional<Video> videoPerId = getVideoPerId(id);
        if (videoPerId.isPresent()){
            return "http://localhost:8080/video/" + id;
        }
        return "wrong id or video does not exists";
    }
    public String getThumbnailUrlPerId(String id){
        Optional<Video> videoPerId = getVideoPerId(id);
        if (videoPerId.isPresent()){
            String name = videoPerId.get().getThumbnailData().getName();
            return "http://localhost:80/" + id + "/" + name;
        }
        return "wrong id or video does not exists";
    }
    public String getTitle(String id){
        Optional<Video> videoPerId = getVideoPerId(id);
        return videoPerId.get().getTitle();
    }

    public List<Video> getTenVideos() {
        List<Video> all = repo.findAll();
       return all.stream().limit(10).collect(Collectors.toList());



    }
}
