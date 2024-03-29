package com.stream.Site;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
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
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.io.InputStream;

@SpringBootApplication
@RestController
public class SiteApplication {
	@Autowired
	private StreamingService service;
	@Autowired
	UserRepo repo;

	@GetMapping(value = "video/{videoId}", produces = "video/mp4")
	public Mono<Resource> getVideos(@PathVariable String videoId, @RequestHeader("Range") String range) {
		System.out.println("range in bytes() : " + range);
		return service.getVideo(videoId);
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
	public long getLong(){return 1233;}

	@Bean
	CommandLineRunner runner(UserRepo repo){
		return args -> {
			User user = new User("jihawuih","Juliu","Klu","Julio","https://google.com","bannerUrl");
			repo.insert(user);
			repo.save(user);
			repo.delete(user);
		};
	}
	public MongoClient reactiveMongoClient() {
		return MongoClients.create("mongodb://exampleapp:example@localhost:27017/exampledb");
	}
}


