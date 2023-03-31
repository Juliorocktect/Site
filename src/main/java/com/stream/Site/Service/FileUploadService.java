package com.stream.Site.Service;

import com.stream.Site.Model.Video;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Service
public class FileUploadService {
    @Autowired
    private VideoService service;
    private final String path = "/home/juli/IdeaProjects/Site/src/main/resources/videos/";
    public void uploadFile(MultipartFile file, String id) throws IOException {
        createNewDirectoryForVideoId(id);
        file.transferTo(new File(path + id+"/" + file.getOriginalFilename()));
    }

    public void createNewDirectoryForVideoId(String id){
        new File(path + id).mkdir();
    }

    public void uploadThumbnail(MultipartFile file,String id) throws IOException {
        file.transferTo(new File(path + id + "/" + file.getOriginalFilename()));
    }
    public String getThumbnailUrl(String id){
        return "http://localhost:8080/getThumbnail?id=" + id;
    }
    public String getVideoUrl(String id){
        Optional<Video> videoPerId = service.getVideoPerId(id);
        if (videoPerId.isPresent()){
            String title = videoPerId.get().getTitle();
            return "http://localhost:8080/video/" + title;
        }
        return "wrong id or video does not exists";
    }

    public String renameFile(String oldname,String newName){
        File file = new File(path + oldname);
        File rename = new File(path + newName);

        boolean status = file.renameTo(rename);

        if (status){
            return "success";
        }
        return "failed";
    }

}
