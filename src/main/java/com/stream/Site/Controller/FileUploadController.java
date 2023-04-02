package com.stream.Site.Controller;

import com.stream.Site.Service.FileUploadService;
import com.stream.Site.Service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RestController
public class FileUploadController {

    @Autowired
    private FileUploadService fileUploadService;
    @Autowired
    VideoService videoService;
    @PostMapping("/uploadVideo")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,@RequestParam("id") String id)throws IOException {
        fileUploadService.uploadFile(file,id);
        videoService.setVideoUrl(id,videoService.getvideoUrlPerId(id));
        return "succes";
    }
    @PostMapping
    public HttpStatus uploadThumbnail(@RequestParam("file") MultipartFile file,@RequestParam("id") String id) throws IOException{
        fileUploadService.uploadThumbnail(file,id);
        videoService.setThumbnailUrl(id,fileUploadService.getThumbnailUrl(id));
        return HttpStatus.OK;
    }

}
