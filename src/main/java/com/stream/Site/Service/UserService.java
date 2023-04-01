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
public class UserService {

    @Autowired
    private UserRepo userRepo;
    private User currentUser;
    public boolean newUser( String passWord,
                            String firstName,
                            String lastName,
                            String userName,
                            String pictureUrl) {
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

    public User getCurrentUser() {
            return currentUser;
    }
    public void removePasswordFromCurrentUser(){
        getCurrentUser().setPassWord(null);
    }
    public List<User> getAllUsers(){
        return userRepo.findAll();
    }
    public User getUserPerId(String Id){
        if (userRepo.findById(Id).isPresent()) {
            return userRepo.findById(Id).get();
        }
        return null;
    }
    public boolean checkIfUserExists( String id){
        User userPerId = getUserPerId(id);
        return userPerId != null;
    }
    public HttpStatus userLogin(String userName,String passWord){
        try
        {
            List<User> allUsers = getAllUsers();
            Optional<User> currentUser = allUsers.stream().filter(p -> p.getUserName().equals(userName)).findFirst();
            User user = currentUser.get();
            if (checkIfPassWordIsTrue(passWord,user)){
                this.currentUser = user;
                return HttpStatus.OK;

            }
        }
        catch (NullPointerException e){
            throw new NullPointerException("Flasche Daten");
        }
        return HttpStatus.BAD_REQUEST;
    }
    public boolean checkIfPassWordIsTrue(String passWord,User user){
        if (Objects.equals(user.getPassWord(), passWord)){
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
    public boolean addToLikedVideos(String videoId){
        User currentuser = getCurrentUser();
        currentuser.addToLikedVideos(videoId);
        userRepo.save(currentuser);
        return true;
    }
    public boolean addToDislikedVideos(String videoId){
        User currentuser = getCurrentUser();
        currentuser.addToDislikedVideos(videoId);
        userRepo.save(currentuser);
        return true; 
        //TODO: add check 
    }


}
