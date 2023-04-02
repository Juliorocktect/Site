package com.stream.Site.Controller;

import com.stream.Site.Model.User;
import com.stream.Site.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.stream.Site.Service.UserService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private final UserService service = new UserService();

    @PostMapping("/create")
    public String create(@RequestBody User user){
        if(service.newUser(user)){
            return "Ok";
        }
        return "Bad Request";
    }
    @GetMapping("/getCurrentUser")
    public User getCurrentUser(){
        service.removePasswordFromCurrentUser();
        return service.getCurrentUser();
    }
    @GetMapping("/getAllUsers")
    public List<User> getAllUsers(){
        return service.getAllUsers();
    }

    //remove due to security problems
    @GetMapping("/getUserPerId/{id}")
    public User getUserPerId(@PathVariable String id){
        return service.getUserPerId(id);
    }

   @PostMapping("/login")
    public HttpStatus login(@RequestBody User user){
        service.userLogin(user.getUserName(), user.getPassWord());
        if (service.userLogin(user.getUserName(),user.getPassWord()).equals(HttpStatus.OK)){
            return HttpStatus.OK;
        }
        return HttpStatus.BAD_REQUEST;
    }
    public HttpStatus addVideoToLikedVideos(String videoId){
        return HttpStatus.OK;
    }


}
