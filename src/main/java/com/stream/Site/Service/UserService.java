package com.stream.Site.Service;

import com.stream.Site.Repository.UserRepo;
import com.stream.Site.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Service
@RestController
public class UserService {
    private User user;
    private UserRepo repo;

    @RequestMapping(
            value = "/createNewUser",
            params = {"firstName",
                    "lastName",
                    "userName",
                    "pictureUrl",
            },
            method = POST)
    public HttpStatus createNewUser(@RequestParam("firstName") String firstName,
                                    @RequestParam("lastName") String lastName,
                                    @RequestParam("userName") String userName,
                                    @RequestParam("pictureUrl") String pictureUrl){
       User user = new User(firstName,lastName,userName,pictureUrl);
       repo.insert(user);
        String userId = user.getId();
        if (repo.findById(user.getId()).isPresent()){
            return HttpStatus.OK;
        }
        return HttpStatus.BAD_REQUEST;
    }


}
