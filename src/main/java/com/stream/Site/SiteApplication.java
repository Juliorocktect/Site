package com.stream.Site;

import com.stream.Site.Repository.UserRepo;
import com.stream.Site.Repository.VideoRepo;
import com.stream.Site.VideoStream.StreamingService;
import org.springframework.beans.factory.annotation.Autowired;
import com.stream.Site.Model.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@SpringBootApplication
@RestController
public class SiteApplication {
	@Autowired
	private StreamingService service;
	@Autowired
	UserRepo repo;

	@GetMapping(value = "video/{title}", produces = "video/mp4")
	public Mono<Resource> getVideos(@PathVariable String title, @RequestHeader("Range") String range) {
		System.out.println("range in bytes() : " + range);
		return service.getVideo(title);
	}

	public static void main(String[] args) {
		SpringApplication.run(SiteApplication.class, args);
	}
	@Bean
	public String getName(){
		return "Julius";
	}

	@Bean
	public int getInt() {
		return 5;
	}
	@Bean
	CommandLineRunner runner(UserRepo repo){
		return args -> {
			User user = new User("jihawuih","Juliu","Klu","Julio","https://google.com");
			repo.insert(user);
			repo.save(user);
		};

	}
}


