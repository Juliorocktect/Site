package com.stream.Site.Service;

import com.stream.Site.Controller.UserController;
import com.stream.Site.Controller.VideoController;
import com.stream.Site.Repository.UserRepo;
import com.stream.Site.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Service
@RestController
public class UserService {

    @Autowired
    private UserRepo userRepo;
    private User user;
    private User currentUser;
    private UserController controller;
    public HttpStatus addToSubscribedToUsers(@PathVariable String Id){
        user.addToSubscribedToUsers(Id);
        return HttpStatus.OK;
    }
    public boolean newUser(String passWord,String firstName,String lastName,String userName,String pictureUrl){
        if (checkIfUserNameIsAvailable(userName)) {
            User user = new User(passWord, firstName, lastName, userName, pictureUrl);
            userRepo.save(user);
            String userId = user.getId();
            if (userRepo.findById(user.getId()).isPresent()) {
                return true;
            }
        }
        return false;
    }
    public List<User> getAllUsers(){
        return userRepo.findAll();
    }
    public Optional<User> getUserPerId(String Id){
        return userRepo.findById(Id);

    }
    public HttpStatus userLogin(String userName,String passWord){
        List<User> allUsers = getAllUsers();
        Optional<User> currentUser = allUsers.stream().filter(p -> p.getUserName().equals(userName)).findFirst();
        User user = currentUser.get();
            if (checkIfPassWordIsTrue(passWord,user)){
                return HttpStatus.OK;
            }
        return HttpStatus.BAD_REQUEST;
    }
    public boolean checkIfPassWordIsTrue(String passWord,User user){
        if (user.getPassWord() == passWord){
            return true;
        }
        return false;

    }
    public boolean checkIfUserNameIsAvailable(String userName){
        List<User> userRepoAll = userRepo.findAll();
        List<User> usersWithSameName = userRepoAll.stream().filter(p -> p.getUserName().equals(userName)).collect(Collectors.toList());
        if (usersWithSameName.isEmpty()){
            return true;
        }
        return false;

    }


}
