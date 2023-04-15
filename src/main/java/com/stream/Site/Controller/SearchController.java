package com.stream.Site.Controller;

import com.stream.Site.Model.Video;
import com.stream.Site.Service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@Controller
@RequestMapping("/search")
public class SearchController {
    @Autowired
    VideoService service;

    @GetMapping("/{text}")
    public List<Video> search(@PathVariable String text) {
        return service.search(text);
    }
}
