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
    private final String path = "C:\\Apache24\\htdocs";
    public void uploadFile(MultipartFile file, String id) throws IOException {
        createNewDirectoryForVideoId(id);
        file.transferTo(new File(path + id+"/" + file.getOriginalFilename()));
        service.setVideoData(id,service.getTitle(id),file.getContentType(),file.getSize());
        service.setVideoUrl(id,service.getvideoUrlPerId(id));
        renameFile(file.getOriginalFilename(),service.getTitle(id),id);
    }

    public void createNewDirectoryForVideoId(String id){
        new File(path + id).mkdir();
    }

    public void uploadThumbnail(MultipartFile file,String id) throws IOException {
        service.setThumbnailData(id,file.getOriginalFilename(),file.getContentType(), file.getSize());
        service.setThumbnailUrl(id,service.getThumbnailUrlPerId(id));
        file.transferTo(new File(path + id + "/" + file.getOriginalFilename()));
    }
    public String getVideoUrl(String id){
        Optional<Video> videoPerId = service.getVideoPerId(id); // ist it already in VideoService?
        if (videoPerId.isPresent()){
            String title = videoPerId.get().getTitle();
            return "http://localhost:8080/video/" + title;
        }
        return "wrong id or video does not exists";
    }

    public String renameFile(String oldname,String newName,String videoId){
        File file = new File(path + videoId +"/" + oldname);
        File rename = new File(path + videoId +"/" + newName + ".mp4");
        boolean status = file.renameTo(rename);

        if (status){
            return "success";
        }
        return "failed";
    }

}
