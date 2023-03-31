package com.stream.Site.Controller;

import com.stream.Site.Model.User;
import com.stream.Site.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.stream.Site.Service.UserService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class UserController {

    @Autowired
    private final UserService service = new UserService();

    @RequestMapping(
            value = "/createNewUser",
            params = {
                    "passWord",
                    "firstName",
                    "lastName",
                    "userName",
                    "pictureUrl",
            },
            method = POST)
    public HttpStatus createNewUser(@RequestParam("passWord") String passWord,
                                    @RequestParam("firstName") String firstName,
                                    @RequestParam("lastName") String lastName,
                                    @RequestParam("userName") String userName,
                                    @RequestParam("pictureUrl") String pictureUrl){
        if(service.newUser(passWord,firstName,lastName,userName,pictureUrl)){
            return HttpStatus.OK;
        }
        return HttpStatus.BAD_REQUEST;

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

    @RequestMapping(
            value = "/login",
            params = {
                    "userName",
                    "passWord",
            },
            method = POST
    )
    public HttpStatus login(@RequestParam String userName,@RequestParam String passWord){
        service.userLogin(userName,passWord);
        if (service.userLogin(userName,passWord).equals(HttpStatus.OK)){
            return HttpStatus.OK;
        }
        return HttpStatus.BAD_REQUEST;
    }


}
