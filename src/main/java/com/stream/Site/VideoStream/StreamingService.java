package com.stream.Site.VideoStream;

import com.stream.Site.Service.FileUploadService;
import com.stream.Site.Service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.InputStream;

@Service
@Component
public class StreamingService {
    @Autowired
    private FileUploadService fileUploadService;
    @Autowired
    private VideoService service;

    private static final String FORMAT="classpath:videos/%s.mp4";

    @Autowired
    private ResourceLoader resourceLoader;


    public Mono<Resource> getVideo(String videoId){
        String title = service.getVideoPerId(videoId).get().getTitle();
        String path = "file:///srv/http/" + videoId +"/" + "%s.mp4";
        return Mono.fromSupplier(() -> resourceLoader.
                getResource(String.format(path, title)));
    }
}
